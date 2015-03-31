package ru.croacker.lbutil.service.ddl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.convertor.CommonResultSetConvertor;
import ru.croacker.lbutil.database.metadata.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

/**
 * Обработчик для извлечения данных о БД из таблиц ML
 */
@Slf4j
@Service
public class MlTablesDdlProcessor implements DatabaseDdlProcessor {

    private static class Query{
        public static final String CLASS = "select * from \"MlClass\"";
        public static final String ATTR = "select * from \"MlAttr\" where \"mlClass\" = {0}";

        public static final String ATTR_GROUP = "select * from \"MlAttrGroup\"";
        public static final String CLASS_ACCESS = "select * from \"MlClassAccess\"";
        public static final String CONTENT_BLOCK = "select * from \"MlContentBlock\"";
        public static final String ENUM = "select * from \"MlEnum\"";
        public static final String FOLDER = "select * from \"MlFolder\"";
        public static final String FOLDER_ACCESS = "select * from \"MlFolderAccess\"";
        public static final String NAVIGATION_BLOCK = "select * from \"MlNavigationBlock\"";
        public static final String OBJECT_CREATE_BLOCK = "select * from \"MlObjectCreateBlock\"";
        public static final String OBJECT_LIST_BLOCK = "select * from \"MlObjectListBlock\"";
        public static final String OBJECT_VIEW_BLOCK = "select * from \"MlObjectViewBlock\"";
        public static final String PAGE = "select * from \"MlPage\"";
        public static final String PAGEACCESS = "select * from \"MlPageAccess\"";
        public static final String PAGE_BLOCK_BASE = "select * from \"MlPageBlockBase\"";
        public static final String ROLE = "select * from \"MlRole\"";
        public static final String SCHEDULED = "select * from \"MlScheduled\"";
        public static final String SEARCH_BLOCK = "select * from \"MlSearchBlock\"";
        public static final String SECURITY_BLOCK = "select * from \"MlSecurityBlock\"";
        public static final String SECURITY_CHANGE_PASSWORD_BLOCK = "select * from \"MlSecurityChangePasswordBlock\"";
        public static final String SECURITY_SETTINGS = "select * from \"MlSecuritySettings\"";
        public static final String SELECT_OBJECTS_BLOCK = "select * from \"MlSelectObjectsBlock\"";
        public static final String SQL_TO_CSV_UTIL = "select * from \"MlSqlToCsvUtil\"";
        public static final String STATIC_PAGEBLOCK = "select * from \"MlStaticPageBlock\"";
        public static final String USER = "select * from \"MlUser\"";
        public static final String UTIL = "select * from \"MlUtil\"";
        public static final String UTIL_ACCESS = "select * from \"MlUtilAccess\"";
        public static final String UTILS_BLOCK = "select * from \"MlUtilsBlock\"";
    }

    @Autowired
    CommonResultSetConvertor resultSetConvertor;

    @Override
    public MlDatabase getMetadata(DataSource dataSource) {
        MlDatabase mlDatabase = (MlDatabase) execSelect(dataSource, getClassHandler(), MlClass.QUE);
        for (MlClass mlClass : mlDatabase.getTables()) {
            mlClass.addColumns(//TODO привязка атрибутов к таблицам не сильно нужна
                    (List<MlAttr>) execSelect(dataSource, getColumnsHandler(),
                            MessageFormat.format(Query.ATTR, mlClass.getId().toString()))
            );
        }

        mlDatabase.getAttrGroups().addAll((Collection<MlAttrGroup>) execSelect(dataSource, getMlTableHandler(MlAttrGroup.class), MlAttrGroup.QUE));
        mlDatabase.getAttrViews().addAll((Collection<MlAttrView>) execSelect(dataSource, getMlTableHandler(MlAttrView.class), MlAttrView.QUE));
        mlDatabase.getClassAccesses().addAll((Collection<MlClassAccess>) execSelect(dataSource, getMlTableHandler(MlClassAccess.class), MlClassAccess.QUE));
        mlDatabase.getContentBlocks().addAll((Collection<MlContentBlock>) execSelect(dataSource, getMlTableHandler(MlContentBlock.class), MlContentBlock.QUE));
        mlDatabase.getEnums().addAll((Collection<MlEnum>) execSelect(dataSource, getMlTableHandler(MlEnum.class), MlEnum.QUE));
        mlDatabase.getFolders().addAll((Collection<MlFolder>) execSelect(dataSource, getMlTableHandler(MlFolder.class), MlFolder.QUE));
        mlDatabase.getFolderAccesses().addAll((Collection<MlFolderAccess>) execSelect(dataSource, getMlTableHandler(MlFolderAccess.class), MlFolderAccess.QUE));
        mlDatabase.getNavigationBlocks().addAll((Collection<MlNavigationBlock>) execSelect(dataSource, getMlTableHandler(MlNavigationBlock.class), MlNavigationBlock.QUE));
        mlDatabase.getObjectCreateBlocks().addAll((Collection<MlObjectCreateBlock>) execSelect(dataSource, getMlTableHandler(MlObjectCreateBlock.class), MlObjectCreateBlock.QUE));
        mlDatabase.getObjectListBlocks().addAll((Collection<MlObjectListBlock>) execSelect(dataSource, getMlTableHandler(MlObjectListBlock.class), MlObjectListBlock.QUE));
        mlDatabase.getObjectViewBlocks().addAll((Collection<MlObjectViewBlock>) execSelect(dataSource, getMlTableHandler(MlObjectViewBlock.class), MlObjectViewBlock.QUE));
        mlDatabase.getPages().addAll((Collection<MlPage>) execSelect(dataSource, getMlTableHandler(MlPage.class), MlPage.QUE));
        mlDatabase.getPageAccesses().addAll((Collection<MlPageAccess>) execSelect(dataSource, getMlTableHandler(MlPageAccess.class), MlPageAccess.QUE));
        mlDatabase.getPageBlockBases().addAll((Collection<MlPageBlockBase>) execSelect(dataSource, getMlTableHandler(MlPageBlockBase.class), MlPageBlockBase.QUE));
        mlDatabase.getRoles().addAll((Collection<MlRole>) execSelect(dataSource, getMlTableHandler(MlRole.class), MlRole.QUE));
        mlDatabase.getScheduleds().addAll((Collection<MlScheduled>) execSelect(dataSource, getMlTableHandler(MlScheduled.class), MlScheduled.QUE));
        mlDatabase.getSearchBlocks().addAll((Collection<MlSearchBlock>) execSelect(dataSource, getMlTableHandler(MlSearchBlock.class), MlSearchBlock.QUE));
        mlDatabase.getSecurityBlocks().addAll((Collection<MlSecurityBlock>) execSelect(dataSource, getMlTableHandler(MlSecurityBlock.class), MlSecurityBlock.QUE));
        mlDatabase.getSecurityChangePasswordBlocks().addAll((Collection<MlSecurityChangePasswordBlock>) execSelect(dataSource, getMlTableHandler(MlSecurityChangePasswordBlock.class), MlSecurityChangePasswordBlock.QUE));
        mlDatabase.getSecuritySettingses().addAll((Collection<MlSecuritySettings>) execSelect(dataSource, getMlTableHandler(MlSecuritySettings.class), MlSecuritySettings.QUE));
        mlDatabase.getSelectObjectsBlocks().addAll((Collection<MlSelectObjectsBlock>) execSelect(dataSource, getMlTableHandler(MlSelectObjectsBlock.class), MlSelectObjectsBlock.QUE));
        mlDatabase.getSqlToCsvUtils().addAll((Collection<MlSqlToCsvUtil>) execSelect(dataSource, getMlTableHandler(MlSqlToCsvUtil.class), MlSqlToCsvUtil.QUE));
        mlDatabase.getStaticPageBlocks().addAll((Collection<MlStaticPageBlock>) execSelect(dataSource, getMlTableHandler(MlStaticPageBlock.class), MlStaticPageBlock.QUE));
        mlDatabase.getUsers().addAll((Collection<MlUser>) execSelect(dataSource, getMlTableHandler(MlUser.class), MlUser.QUE));
        mlDatabase.getUtils().addAll((Collection<MlUtil>) execSelect(dataSource, getMlTableHandler(MlUtil.class), MlUtil.QUE));
        mlDatabase.getUtilAccesses().addAll((Collection<MlUtilAccess>) execSelect(dataSource, getMlTableHandler(MlUtilAccess.class), MlUtilAccess.QUE));
        mlDatabase.getUtilsBlocks().addAll((Collection<MlUtilsBlock>) execSelect(dataSource, getMlTableHandler(MlUtilsBlock.class), MlUtilsBlock.QUE));

        //Обработка таблиц связей
        List<String> mnRelationsTable = Lists.newArrayList();
        Database database = getDatabaseModel(dataSource);
        for (Table table : database.getTables()) {
            if (table.getName().startsWith("MN_")) {
                Collection<CommonMlUnit> mnRelations = (Collection<CommonMlUnit>) execSelect(dataSource, getMlTableHandler(CommonMlUnit.class), MessageFormat.format(CommonMlUnit.QUE, table.getName()));
                for(CommonMlUnit commonMlUnit: mnRelations){
                    commonMlUnit.setMlClassTableName(table.getName());
                }
                mlDatabase.getMnUnits().putAll(table.getName(), mnRelations);
            }
        }



        return mlDatabase;
    }

    /**
     * Выполнить запрос на выборку
     * @param dataSource
     * @param handler
     * @param que
     * @return
     */
    private Object execSelect(DataSource dataSource, ResultSetHandler handler, String que) {
        Object result = null;
        try(Connection connection = dataSource.getConnection()) {
            QueryRunner queryRunner = new QueryRunner();
            result = queryRunner.query(connection, que, handler);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Хендлер для получения таблиц
     *
     * @return
     */
    private ResultSetHandler<MlDatabase> getClassHandler() {
        return new ResultSetHandler<MlDatabase>() {
            @Override
            public MlDatabase handle(ResultSet resultSet) throws SQLException {
                MlDatabase mlDatabase = new MlDatabase(true);
                while (resultSet.next()) {
                    MlClass mlClass = resultSetConvertor.toMetadata(resultSet, MlClass.class);
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

    /**
     * Хендлер для получения данных таблицы
     *
     * @return
     */
    private ResultSetHandler<List<? extends MlUnit>> getMlTableHandler(final Class<? extends MlUnit> clazz) {
        return new ResultSetHandler<List<? extends MlUnit>>() {
            @Override
            public List<MlUnit> handle(ResultSet resultSet) throws SQLException {
                List<MlUnit> columns = Lists.newArrayList();
                while (resultSet.next()) {
                    columns.add(resultSetConvertor.toMetadata(resultSet, clazz));
                }
                return columns;
            }
        };
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
