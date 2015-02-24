package ru.croacker.lbutil.service;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
   *
   * @param dataSource
   * @return
   */
  public MlDatabase getMlDatabaseModelFromMlClass(DataSource dataSource){
    MlDatabase mlDatabase = new MlDatabase();
    ResultSet resultSetClass = execSelect(dataSource, "select * from MlClass;");
    try {
      while (resultSetClass.next()) {
        ResultSet resultSetAttr = execSelect(dataSource, "select * from MlAttr where mlClass = " + resultSetClass.getString(0));
        //конвертируем класс
        while (resultSetAttr.next()){
          //конвертируем поля
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return mlDatabase;
  }

  private ResultSet execSelect(DataSource dataSource, String que){
    ResultSet resultSet = null;
    try {
      Connection connection = dataSource.getConnection();
      Statement statement = connection.createStatement();
      resultSet = statement.executeQuery(que);
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
    return resultSet;
  }

  private String getDatabaseName(DataSource dataSource) throws SQLException {
    Connection connection = dataSource.getConnection();
    return connection.getCatalog();
  }

}
