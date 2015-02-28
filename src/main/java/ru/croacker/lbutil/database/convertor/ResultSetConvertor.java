package ru.croacker.lbutil.database.convertor;

import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 */
@Slf4j
public abstract class ResultSetConvertor {

  protected Long getLong(ResultSet resultSet, String fieldName) {
    try {
      return resultSet.getLong(fieldName);
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  protected String getString(ResultSet resultSet, String fieldName) {
    try {
      return resultSet.getString(fieldName);
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  protected Boolean getBoolean(ResultSet resultSet, String fieldName) {
    try {
      return resultSet.getBoolean(fieldName);
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  protected Date getDate(ResultSet resultSet, String fieldName) {
    try {
      return resultSet.getDate(fieldName);
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

}
