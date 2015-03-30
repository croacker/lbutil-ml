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
//    MlClass mlClass = new MlClass();
//    mlClass.setId(getLong(resultSet, "id"));
//    mlClass.setTableName(getString(resultSet, "tableName"));
//    mlClass.setEntityName(getString(resultSet, "entityName"));
//    mlClass.setIsSystem(getBoolean(resultSet, "isSystem"));
//    mlClass.setJavaClass(getString(resultSet, "javaClass"));
//    mlClass.setHasHistory(getBoolean(resultSet, "hasHistory"));
//    mlClass.setDescription(getString(resultSet, "description"));
//    mlClass.setParent(getLong(resultSet, "parent"));
//    mlClass.setIsCacheable(getBoolean(resultSet, "isCacheable"));
//    mlClass.setTitleFormat(getString(resultSet, "titleFormat"));
//    mlClass.setHandlerClassName(getString(resultSet, "handlerClassName"));
//    mlClass.setReplicationHandlerClassName(getString(resultSet, "replicationHandlerClassName"));
//    return mlClass;
    return null;
  }

  @Override
  public ResultSet toTable(MlClass metadata) {
    return null;
  }

}