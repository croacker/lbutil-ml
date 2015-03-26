package ru.croacker.lbutil.database.convertor;

import org.apache.commons.lang.StringUtils;
import org.apache.ddlutils.model.Column;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.metadata.MlAttr;

/**
 * Конвертер для колонки
 */
@Service
public class CommonColumnConvertor implements Convertor<Column, MlAttr> {

  @Override
  public MlAttr toMetadata(Column column) {
    MlAttr mlAttr = new MlAttr();
    mlAttr.setTableFieldName(column.getName());
    mlAttr.setEntityFieldName(column.getName());
    mlAttr.setSystemField(false);
    mlAttr.setPrimaryKey(column.isPrimaryKey());
    mlAttr.setAutoIncrement(column.isAutoIncrement());
    mlAttr.setFieldType(getFieldType(column));
    mlAttr.setLinkAttr(null);
    mlAttr.setMlClass(null);
    mlAttr.setLinkClass(null);
    mlAttr.setLinkFilter(null);
    mlAttr.setInForm(true);
    mlAttr.setUseInSimpleSearch(true);
    mlAttr.setUseInExtendedSearch(true);
    mlAttr.setFieldFormat(null);
    mlAttr.setGroup(null);
    mlAttr.setDescription(getDescription(column));
    mlAttr.setDefaultValue(column.getDefaultValue());
    mlAttr.setVirtual(false);
    mlAttr.setLongLinkValue(null);
    mlAttr.setReadOnly(false);

    mlAttr.setFieldTypeName(getFieldTypeName(mlAttr.getFieldType()));
    return mlAttr;
  }

  @Override
  public Column toTable(MlAttr mlAttr) {
    Column column = new Column();

    return column;
  }

  private String getFieldType(Column column) {
    if(column.isOfTextType()){
      return "STRING";
    }else if(column.isOfNumericType()){
      return "LONG";
    }else if(column.isOfBinaryType()){
      return "FILE";
    }

    return "STRING";
  }

  private String getFieldTypeName(String fieldType) {
    if(fieldType.equals("STRING")){
      return "String";
    }else if(fieldType.equals("LONG")){
      return "Long";
    }else if(fieldType.equals("FILE")){
      return "byte[]";
    }
    return "String";
  }

  private String getDescription(Column column) {
    return StringUtils.isEmpty(column.getDescription()) ? column.getName() : column.getDescription();
  }

}
