package ru.croacker.lbutil.database.convertor;

import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.metadata.MlClass;

import java.sql.ResultSet;

/**
 *
 */
@Service
public class CommonResultSetMlTableConvertor extends ResultSetConvertor implements Convertor<ResultSet, MlClass> {

  @Override
  public MlClass toMetadata(ResultSet resultSet) {
    MlClass mlClass = new MlClass();
    mlClass.setId(getLong(resultSet, "id"));
    mlClass.set("tableName", getString(resultSet, "tableName"));
    mlClass.set("entityName", getString(resultSet, "entityName"));
    mlClass.set("isSystem", getBoolean(resultSet, "isSystem"));
    mlClass.set("javaClass", getString(resultSet, "javaClass"));
    mlClass.set("hasHistory", getBoolean(resultSet, "hasHistory"));
    mlClass.set("description", getString(resultSet, "description"));
    mlClass.set("parent", getLong(resultSet, "parent"));
    mlClass.set("isCacheable", getBoolean(resultSet, "isCacheable"));
    mlClass.set("titleFormat", getString(resultSet, "titleFormat"));
    mlClass.set("handlerClassName", getString(resultSet, "handlerClassName"));
    mlClass.set("replicationHandlerClassName", getString(resultSet, "replicationHandlerClassName"));
    return mlClass;
  }

  @Override
  public ResultSet toTable(MlClass metadata) {
    return null;
  }

}