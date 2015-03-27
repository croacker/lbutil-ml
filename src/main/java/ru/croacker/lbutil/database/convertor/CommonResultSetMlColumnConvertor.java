package ru.croacker.lbutil.database.convertor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.metadata.MlAttr;

import java.sql.ResultSet;

/**
 *
 */
@Service
@Slf4j
public class CommonResultSetMlColumnConvertor extends ResultSetConvertor implements Convertor<ResultSet, MlAttr> {

    @Override
    public MlAttr toMetadata(ResultSet resultSet) {
        MlAttr mlAttr = new MlAttr();
        mlAttr.setId(getLong(resultSet, "id"));
        mlAttr.set("tableFieldName", getString(resultSet, "tableFieldName"));
        mlAttr.set("entityFieldName", getString(resultSet, "entityFieldName"));
        mlAttr.set("fieldType", getString(resultSet, "fieldType"));
        mlAttr.set("replicationType", getString(resultSet, "replicationType"));
        mlAttr.set("guid", getString(resultSet, "guid"));
        mlAttr.set("lastChange", getDate(resultSet, "lastChange"));
        mlAttr.set("virtual", getBoolean(resultSet, "virtual"));
        mlAttr.set("ordered", getBoolean(resultSet, "ordered"));
        mlAttr.set("description", getString(resultSet, "description"));
        mlAttr.set("defaultValue", getString(resultSet, "defaultValue"));
        mlAttr.set("longLinkValue", getString(resultSet, "longLinkValue"));
        mlAttr.set("manyToManyTableName", getString(resultSet, "manyToManyTableName"));
        mlAttr.set("manyToManyFieldNameM", getString(resultSet, "manyToManyFieldNameM"));
        mlAttr.set("manyToManyFieldNameN", getString(resultSet, "manyToManyFieldNameN"));
        mlAttr.set("notShowCreate", getBoolean(resultSet, "notShowCreate"));
        mlAttr.set("notShowChoose", getBoolean(resultSet, "notShowChoose"));
        mlAttr.set("notShowEdit", getBoolean(resultSet, "notShowEdit"));
        mlAttr.set("notShowDelete", getBoolean(resultSet, "notShowDelete"));
        mlAttr.set("notShowCreateInEdit", getBoolean(resultSet, "notShowCreateInEdit"));
        mlAttr.set("notShowChooseInEdit", getBoolean(resultSet, "notShowChooseInEdit"));
        mlAttr.set("notShowEditInEdit", getBoolean(resultSet, "notShowEditInEdit"));
        mlAttr.set("notShowDeleteInEdit", getBoolean(resultSet, "notShowDeleteInEdit"));
        mlAttr.set("newLine", getBoolean(resultSet, "newLine"));
        mlAttr.set("viewPos", getLong(resultSet, "viewPos"));
        mlAttr.set("offset", getLong(resultSet, "offset"));
        mlAttr.set("totalLength", getLong(resultSet, "totalLength"));
        mlAttr.set("titleLength", getLong(resultSet, "titleLength"));
        mlAttr.set("tableHeight", getLong(resultSet, "tableHeight"));
        mlAttr.set("mandatory", getBoolean(resultSet, "mandatory"));
        mlAttr.set("inputmask", getString(resultSet, "inputmask"));
        mlAttr.set("readOnly", getBoolean(resultSet, "readOnly"));
        mlAttr.set("templateView", getString(resultSet, "templateView"));
        mlAttr.set("templateEdit", getString(resultSet, "templateEdit"));
        mlAttr.set("templateCreate", getString(resultSet, "templateCreate"));
        mlAttr.set("primaryKey", getBoolean(resultSet, "primaryKey"));
        mlAttr.set("autoIncrement", getBoolean(resultSet, "autoIncrement"));
        mlAttr.set("linkAttr", getLong(resultSet, "linkAttr"));
        mlAttr.set("mlClass", getLong(resultSet, "mlClass"));
        mlAttr.set("linkClass", getLong(resultSet, "linkClass"));
        mlAttr.set("defaultSqlValue", getString(resultSet, "defaultSqlValue"));
        mlAttr.set("useInSimpleSearch", getBoolean(resultSet, "useInSimpleSearch"));
        mlAttr.set("useInExtendedSearch", getBoolean(resultSet, "useInExtendedSearch"));
        mlAttr.set("systemField", getBoolean(resultSet, "systemField"));

        mlAttr.set("tableFieldName", getFieldTypeName(String.valueOf(mlAttr.get("fieldType"))));
        return mlAttr;
    }

    @Override
    public ResultSet toTable(MlAttr metadata) {
        return null;
    }

    /**
     * Имя поля для создания классов
     *
     * @param fieldType
     * @return
     */
    private String getFieldTypeName(String fieldType) {
        if (fieldType.equals("STRING")) {
            return "String";
        } else if (fieldType.equals("LONG")) {
            return "Long";
        } else if (fieldType.equals("FILE")) {
            return "byte[]";
        } else if (fieldType.equals("BOOLEAN")) {
            return "Boolean";
        } else if (fieldType.equals("DATE")) {
            return "Date";
        } else if (fieldType.equals("ENUM")) {
            return "String";
        } else if (fieldType.equals("DOUBLE")) {
            return "Double";
        } else if (fieldType.equals("TEXT")) {
            return "Clob";
        }
        //Для "MANY_TO_ONE" указывается linkClass
        //Для "ONE_TO_MANY" указывается linkAttr
        return fieldType;
    }

}
