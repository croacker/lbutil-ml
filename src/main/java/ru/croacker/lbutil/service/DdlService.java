package ru.croacker.lbutil.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.model.Database;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
@Service
@Slf4j
public class DdlService {

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

  private String getDatabaseName(DataSource dataSource) throws SQLException {
    Connection connection = dataSource.getConnection();
    return connection.getCatalog();
  }

}
