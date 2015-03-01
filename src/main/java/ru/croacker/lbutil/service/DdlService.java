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
import ru.croacker.lbutil.database.convertor.CommonResultSetColumnConvertor;
import ru.croacker.lbutil.database.convertor.CommonResultSetMlTableConvertor;
import ru.croacker.lbutil.database.convertor.CommonTableConvertor;
import ru.croacker.lbutil.database.metadata.MlColumn;
import ru.croacker.lbutil.database.metadata.MlDatabase;
import ru.croacker.lbutil.database.metadata.MlTable;

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

  private static final List<String> excludeTables = new ArrayList(){{
    add("databasechangelog");
    add("databasechangeloglock");
  }};

  @Autowired
  CommonTableConvertor tableConvertor;
  @Autowired
  CommonResultSetMlTableConvertor resultSetTableConvertor;
  @Autowired
  CommonResultSetColumnConvertor resultSetColumnConvertor;

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
   *
   * @param dataSource
   * @return
   */
  public MlDatabase getMlDatabaseModel(DataSource dataSource){
    MlDatabase mlDatabase = new MlDatabase();
    Database database = getDatabaseModel(dataSource);
    for(Table table:database.getTables()){//TODO Добавить упорядочивание, сначала должны идти Системные таблицы
      if(excludeTables.contains(table.getName())){
        continue;
      }
      mlDatabase.addTable(tableConvertor.toMetadata(table));
    }
    return mlDatabase;
  }

  /**
   *
   * @param dataSource
   * @return
   */
  public boolean mlClassExists(DataSource dataSource){
    boolean exists = false;
    Database database = getDatabaseModel(dataSource);
    for(Table table: database.getTables()){
      exists = table.getName().equalsIgnoreCase("MlClass");
      if(exists){
        break;
      }
    }
    return exists;
  }

  /**
   * Получить классы из таблиц MlClass и MlAttr
   * @param dataSource
   * @return
   */
  public MlDatabase getMlDatabaseModelFromMlClass(DataSource dataSource){
    MlDatabase mlDatabase = (MlDatabase) execSelect(dataSource, getTableHandler(), "select * from \"MlClass\"");
    for (MlTable mlTable:mlDatabase.getTables()){
      mlTable.addColumns(
          (List<MlColumn>) execSelect(dataSource, getColumnsHandler(),
              "select * from \"MlAttr\" where \"mlClass\" = " + mlTable.getId())
      );
    }
    return mlDatabase;
  }

  /**
   *
   * @param dataSource
   * @param handler
   * @param que
   * @return
   */
  private Object execSelect(DataSource dataSource, ResultSetHandler handler, String que){
    Object result = null;
    Connection connection = null;
    try {
      connection = dataSource.getConnection();
      QueryRunner queryRunner = new QueryRunner();
      result = queryRunner.query(connection, que, handler);
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
    }finally {
      closeConnection(connection);
    }
    return result;
  }

  private void closeConnection(Connection connection){
    if(connection != null){
      try {
        DbUtils.close(connection);
      } catch (SQLException e) {
        log.error(e.getMessage(), e);
      }
    }
  }

  /**
   * Хендлер для получения таблиц
   * @return
   */
  private ResultSetHandler<MlDatabase> getTableHandler(){
    return new ResultSetHandler<MlDatabase>() {
      @Override
      public MlDatabase handle(ResultSet resultSet) throws SQLException {
        MlDatabase mlDatabase = new MlDatabase();
        while (resultSet.next()) {
          MlTable mlTable = resultSetTableConvertor.toMetadata(resultSet);
          mlDatabase.addTable(mlTable);
        }
        return mlDatabase;
      }
    };
  }

  /**
   * Хендлер для получения колонок
   * @return
   */
  private ResultSetHandler<List<MlColumn>> getColumnsHandler(){
    return new ResultSetHandler<List<MlColumn>>() {
      @Override
      public List<MlColumn> handle(ResultSet resultSet) throws SQLException {
        List<MlColumn> columns = Lists.newArrayList();
        while (resultSet.next()) {
          columns.add(resultSetColumnConvertor.toMetadata(resultSet));
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
