package ru.croacker.lbutil.service.ddl;

import lombok.extern.slf4j.Slf4j;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.convertor.CommonTableConvertor;
import ru.croacker.lbutil.database.metadata.MlDatabase;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Обработчик для извлечения данных о БД из схемы
 */
@Slf4j
@Service
public class SchemaDdlProcessor implements DatabaseDdlProcessor {

    @Autowired
    CommonTableConvertor tableConvertor;

    private static final List<String> excludeTables = new ArrayList() {{
        add("databasechangelog");
        add("databasechangeloglock");
    }};

    @Override
    public MlDatabase getMetadata(DataSource dataSource) {
        MlDatabase mlDatabase = new MlDatabase(false);
        Database database = getDatabaseModel(dataSource);
        for (Table table : database.getTables()) {
            if (excludeTables.contains(table.getName())) {
                continue;
            }
            mlDatabase.addTable(tableConvertor.toMetadata(table));
        }
        return mlDatabase;
    }

    /**
     * Получить модель БД как Apache DDL
     * @param dataSource
     * @return
     */
    private Database getDatabaseModel(DataSource dataSource) {
        String databaseName;
        try {
            databaseName = getDatabaseName(dataSource);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        Platform platform = PlatformFactory.createNewPlatformInstance(dataSource);
        return platform.readModelFromDatabase(databaseName);
    }

    /**
     * Получить имя БД
     * @param dataSource
     * @return
     * @throws SQLException
     */
    private String getDatabaseName(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection.getCatalog();
    }
}

