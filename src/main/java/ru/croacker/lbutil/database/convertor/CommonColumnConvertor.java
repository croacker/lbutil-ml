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
        mlAttr.set("tableFieldName", column.getName());
        mlAttr.set("entityFieldName", column.getName());
        mlAttr.set("systemField", false);
        mlAttr.set("primaryKey", column.isPrimaryKey());
        mlAttr.set("autoIncrement", column.isAutoIncrement());
        mlAttr.set("fieldType", getFieldType(column));
        mlAttr.set("linkAttr", null);
        mlAttr.set("mlClass", null);
        mlAttr.set("linkClass", null);
        mlAttr.set("linkFilter", null);
        mlAttr.set("inForm", true);
        mlAttr.set("inList", true);
        mlAttr.set("useInSimpleSearch", true);
        mlAttr.set("useInExtendedSearch", true);
        mlAttr.set("fieldFormat", null);
        mlAttr.set("group", null);
        mlAttr.set("description", getDescription(column));
        mlAttr.set("defaultValue", column.getDefaultValue());
        mlAttr.set("virtual", false);
        mlAttr.set("longLinkValue", null);
        mlAttr.set("readOnly", false);

        mlAttr.setFieldTypeName(getFieldTypeName(String.valueOf(mlAttr.get("fieldType"))));
        return mlAttr;
    }

    @Override
    public Column toTable(MlAttr mlAttr) {
        Column column = new Column();

        return column;
    }

    private String getFieldType(Column column) {
        if (column.isOfTextType()) {
            return "STRING";
        } else if (column.isOfNumericType()) {
            return "LONG";
        } else if (column.isOfBinaryType()) {
            return "FILE";
        }

        return "STRING";
    }

    private String getFieldTypeName(String fieldType) {
        if (fieldType.equals("STRING")) {
            return "String";
        } else if (fieldType.equals("LONG")) {
            return "Long";
        } else if (fieldType.equals("FILE")) {
            return "byte[]";
        }
        return "String";
    }

    private String getDescription(Column column) {
        return StringUtils.isEmpty(column.getDescription()) ? column.getName() : column.getDescription();
    }

}
