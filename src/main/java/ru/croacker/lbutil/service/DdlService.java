package ru.croacker.lbutil.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.convertor.CommonResultSetConvertor;
import ru.croacker.lbutil.database.convertor.CommonTableConvertor;
import ru.croacker.lbutil.database.metadata.MlAttr;
import ru.croacker.lbutil.database.metadata.MlDatabase;
import ru.croacker.lbutil.database.metadata.MlClass;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
@Slf4j
public class DdlService {

    private static final List<String> excludeTables = new ArrayList() {{
        add("databasechangelog");
        add("databasechangeloglock");
    }};

    @Autowired
    CommonTableConvertor tableConvertor;
    @Autowired
    CommonResultSetConvertor resultSetTableConvertor;
    @Autowired
//  CommonResultSetMlColumnConvertor resultSetConvertor;
            CommonResultSetConvertor resultSetConvertor;

    public Database getDatabaseModel(DataSource dataSource) {
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
     * @param dataSource
     * @return
     */
    public MlDatabase getMlDatabaseModel(DataSource dataSource) {
        MlDatabase mlDatabase = new MlDatabase(false);
        Database database = getDatabaseModel(dataSource);
        for (Table table : database.getTables()) {//TODO Добавить упорядочивание, сначала должны идти Системные таблицы
            if (excludeTables.contains(table.getName())) {
                continue;
            }
            mlDatabase.addTable(tableConvertor.toMetadata(table));
        }
        return mlDatabase;
    }

    /**
     * @param dataSource
     * @return
     */
    public boolean mlClassExists(DataSource dataSource) {
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
     * Получить классы из таблиц MlClass и MlAttr
     *
     * @param dataSource
     * @return
     */
    public MlDatabase getMlDatabaseModelFromMlClass(DataSource dataSource) {
        MlDatabase mlDatabase = (MlDatabase) execSelect(dataSource, getTableHandler(), "select * from \"MlClass\"");
        for (MlClass mlClass : mlDatabase.getTables()) {
            mlClass.addColumns(
                    (List<MlAttr>) execSelect(dataSource, getColumnsHandler(),
                            "select * from \"MlAttr\" where \"mlClass\" = " + mlClass.getId())
            );
        }
        return mlDatabase;
    }

    /**
     * @param dataSource
     * @param handler
     * @param que
     * @return
     */
    private Object execSelect(DataSource dataSource, ResultSetHandler handler, String que) {
        Object result = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            result = queryRunner.query(connection, que, handler);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                DbUtils.close(connection);
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Хендлер для получения таблиц
     *
     * @return
     */
    private ResultSetHandler<MlDatabase> getTableHandler() {
        return new ResultSetHandler<MlDatabase>() {
            @Override
            public MlDatabase handle(ResultSet resultSet) throws SQLException {
                MlDatabase mlDatabase = new MlDatabase(true);
                while (resultSet.next()) {
                    MlClass mlClass = resultSetTableConvertor.toMetadata(resultSet, MlClass.class);
                    mlDatabase.addTable(mlClass);
                }
                return mlDatabase;
            }
        };
    }

    /**
     * Хендлер для получения колонок
     *
     * @return
     */
    private ResultSetHandler<List<MlAttr>> getColumnsHandler() {
        return new ResultSetHandler<List<MlAttr>>() {
            @Override
            public List<MlAttr> handle(ResultSet resultSet) throws SQLException {
                List<MlAttr> columns = Lists.newArrayList();
                while (resultSet.next()) {
                    columns.add(resultSetConvertor.toMetadata(resultSet, MlAttr.class));
                }
                return columns;
            }
        };
    }

    private String getDatabaseName(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection.getCatalog();
    }

}
