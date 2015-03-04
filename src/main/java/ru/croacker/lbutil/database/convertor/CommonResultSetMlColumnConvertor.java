package ru.croacker.lbutil.database.convertor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.metadata.MlColumn;

import java.sql.ResultSet;

/**
 *
 */
@Service
@Slf4j
public class CommonResultSetMlColumnConvertor extends ResultSetConvertor implements Convertor<ResultSet, MlColumn> {
  @Override
  public MlColumn toMetadata(ResultSet resultSet) {
    MlColumn mlColumn = new MlColumn();
    mlColumn.setId(getLong(resultSet, "id"));
    mlColumn.setTableFieldName(getString(resultSet, "tableFieldName"));
    mlColumn.setEntityFieldName(getString(resultSet, "entityFieldName"));
    mlColumn.setFieldType(getString(resultSet, "fieldType"));
    mlColumn.setReplicationType(getString(resultSet, "replicationType"));
    mlColumn.setGuid(getString(resultSet, "guid"));
    mlColumn.setLastChange(getDate(resultSet, "lastChange"));
    mlColumn.setVirtual(getBoolean(resultSet, "virtual"));
    mlColumn.setOrdered(getBoolean(resultSet, "ordered"));
    mlColumn.setDescription(getString(resultSet, "description"));
    mlColumn.setDefaultValue(getString(resultSet, "defaultValue"));
    mlColumn.setLongLinkValue(getString(resultSet, "longLinkValue"));
    mlColumn.setManyToManyTableName(getString(resultSet, "manyToManyTableName"));
    mlColumn.setManyToManyFieldNameM(getString(resultSet, "manyToManyFieldNameM"));
    mlColumn.setManyToManyFieldNameN(getString(resultSet, "manyToManyFieldNameN"));
    mlColumn.setNotShowCreate(getBoolean(resultSet, "notShowCreate"));
    mlColumn.setNotShowChoose(getBoolean(resultSet, "notShowChoose"));
    mlColumn.setNotShowEdit(getBoolean(resultSet, "notShowEdit"));
    mlColumn.setNotShowDelete(getBoolean(resultSet, "notShowDelete"));
    mlColumn.setNotShowCreateInEdit(getBoolean(resultSet, "notShowCreateInEdit"));
    mlColumn.setNotShowChooseInEdit(getBoolean(resultSet, "notShowChooseInEdit"));
    mlColumn.setNotShowEditInEdit(getBoolean(resultSet, "notShowEditInEdit"));
    mlColumn.setNotShowDeleteInEdit(getBoolean(resultSet, "notShowDeleteInEdit"));
    mlColumn.setNewLine(getBoolean(resultSet, "newLine"));
    mlColumn.setViewPos(getLong(resultSet, "viewPos"));
    mlColumn.setOffset(getLong(resultSet, "offset"));
    mlColumn.setTotalLength(getLong(resultSet, "totalLength"));
    mlColumn.setTitleLength(getLong(resultSet, "titleLength"));
    mlColumn.setTableHeight(getLong(resultSet, "tableHeight"));
    mlColumn.setMandatory(getBoolean(resultSet, "mandatory"));
    mlColumn.setInputmask(getString(resultSet, "inputmask"));
    mlColumn.setReadOnly(getBoolean(resultSet, "readOnly"));
    mlColumn.setTemplateView(getString(resultSet, "templateView"));
    mlColumn.setTemplateEdit(getString(resultSet, "templateEdit"));
    mlColumn.setTemplateCreate(getString(resultSet, "templateCreate"));
    mlColumn.setPrimaryKey(getBoolean(resultSet, "primaryKey"));
    mlColumn.setAutoIncrement(getBoolean(resultSet, "autoIncrement"));
    mlColumn.setLinkAttr(getLong(resultSet, "linkAttr"));
    mlColumn.setMlClass(getLong(resultSet, "mlClass"));
    mlColumn.setLinkClass(getLong(resultSet, "linkClass"));
    mlColumn.setDefaultSqlValue(getString(resultSet, "defaultSqlValue"));
    mlColumn.setUseInSimpleSearch(getBoolean(resultSet, "useInSimpleSearch"));
    mlColumn.setUseInExtendedSearch(getBoolean(resultSet, "useInExtendedSearch"));
    mlColumn.setSystemField(getBoolean(resultSet, "systemField"));

    mlColumn.setFieldTypeName(getFieldTypeName(mlColumn.getFieldType()));
    return mlColumn;
  }

  @Override
  public ResultSet toTable(MlColumn metadata) {
    return null;
  }

  /**
   * Имя поля для создания классов
   * @param fieldType
   * @return
   */
  private String getFieldTypeName(String fieldType) {
    if(fieldType.equals("STRING")){
      return "String";
    }else if(fieldType.equals("LONG")){
      return "Long";
    }else if(fieldType.equals("FILE")){
      return "byte[]";
    }else if(fieldType.equals("BOOLEAN")){
      return "Boolean";
    }else if(fieldType.equals("DATE")){
      return "Date";
    }else if(fieldType.equals("ENUM")){
      return "String";
    }else if(fieldType.equals("DOUBLE")){
      return "Double";
    }else if(fieldType.equals("TEXT")){
      return "Clob";
    }
    //Для "MANY_TO_ONE" указывается linkClass
    //Для "ONE_TO_MANY" указывается linkAttr
    return fieldType;
  }

}
