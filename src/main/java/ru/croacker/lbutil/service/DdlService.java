package ru.croacker.lbutil.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.metadata.MlDatabase;
import ru.croacker.lbutil.service.ddl.MlTablesDdlProcessor;
import ru.croacker.lbutil.service.ddl.SchemaDdlProcessor;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 *
 */
@Service
@Slf4j
public class DdlService {

    @Autowired
    SchemaDdlProcessor schemaDdlProcessor;
    @Autowired
    MlTablesDdlProcessor mlTablesDdlProcessor;

    /**
     * Получить модель БД на основании схемы
     * @param dataSource
     * @return
     */
    public MlDatabase getMlDatabaseModel(DataSource dataSource) {
        return schemaDdlProcessor.getMetadata(dataSource);
    }

    /**
     * Получить классы из таблиц Ml
     *
     * @param dataSource
     * @return
     */
    public MlDatabase getMlDatabaseModelFromMlTables(DataSource dataSource) {
        return mlTablesDdlProcessor.getMetadata(dataSource);
    }

    /**
     * Эта БД создана с использоаванием ML-framework
     * @param dataSource
     * @return
     */
    public boolean isMlDatabase(DataSource dataSource) {
        boolean exists = false;
        Database database = getDatabaseModel(dataSource);
        for (Table table : database.getTables()) {
            exists = table.getName().equalsIgnoreCase("MlClass");
            if (exists) {
                break;
            }
        }
        return exists;
    }

    /**
     * Получить модель БД как Apache DDL
     * @param dataSource
     * @return
     */
    public Database getDatabaseModel(DataSource dataSource) {
        String databaseName;
        try {
            databaseName = dataSource.getConnection().getCatalog();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        Platform platform = PlatformFactory.createNewPlatformInstance(dataSource);
        return platform.readModelFromDatabase(databaseName);
    }
}
