package ru.croacker.lbutil.database.convertor;

import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.metadata.MlTable;

import java.sql.ResultSet;

/**
 *
 */
@Service
public class CommonResultSetTableConverter extends ResultSetConvertor implements Convertor<ResultSet, MlTable> {

  @Override
  public MlTable toMetadata(ResultSet resultSet) {
    MlTable mlTable = new MlTable();
    mlTable.setId(getLong(resultSet, "id"));
    mlTable.setTableName(getString(resultSet, "tableName"));
    mlTable.setEntityName(getString(resultSet, "entityName"));
    mlTable.setIsSystem(getBoolean(resultSet, "isSystem"));
    mlTable.setJavaClass(getString(resultSet, "javaClass"));
    mlTable.setHasHistory(getBoolean(resultSet, "hasHistory"));
    mlTable.setDescription(getString(resultSet, "description"));
    mlTable.setParent(getLong(resultSet, "parent"));
    mlTable.setIsCacheable(getBoolean(resultSet, "isCacheable"));
    mlTable.setTitleFormat(getString(resultSet, "titleFormat"));
    mlTable.setHandlerClassName(getString(resultSet, "handlerClassName"));
    mlTable.setReplicationHandlerClassName(getString(resultSet, "replicationHandlerClassName"));
    return mlTable;
  }

  @Override
  public ResultSet toTable(MlTable metadata) {
    return null;
  }

}