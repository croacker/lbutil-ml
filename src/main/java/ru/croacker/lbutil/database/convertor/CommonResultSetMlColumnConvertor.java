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
        mlAttr.setTableFieldName(getString(resultSet, "tableFieldName"));
        mlAttr.setEntityFieldName(getString(resultSet, "entityFieldName"));
        mlAttr.setFieldType(getString(resultSet, "fieldType"));
        mlAttr.setReplicationType(getString(resultSet, "replicationType"));
        mlAttr.setGuid(getString(resultSet, "guid"));
        mlAttr.setLastChange(getDate(resultSet, "lastChange"));
        mlAttr.setVirtual(getBoolean(resultSet, "virtual"));
        mlAttr.setOrdered(getBoolean(resultSet, "ordered"));
        mlAttr.setDescription(getString(resultSet, "description"));
        mlAttr.setDefaultValue(getString(resultSet, "defaultValue"));
        mlAttr.setLongLinkValue(getString(resultSet, "longLinkValue"));
        mlAttr.setManyToManyTableName(getString(resultSet, "manyToManyTableName"));
        mlAttr.setManyToManyFieldNameM(getString(resultSet, "manyToManyFieldNameM"));
        mlAttr.setManyToManyFieldNameN(getString(resultSet, "manyToManyFieldNameN"));
        mlAttr.setNotShowCreate(getBoolean(resultSet, "notShowCreate"));
        mlAttr.setNotShowChoose(getBoolean(resultSet, "notShowChoose"));
        mlAttr.setNotShowEdit(getBoolean(resultSet, "notShowEdit"));
        mlAttr.setNotShowDelete(getBoolean(resultSet, "notShowDelete"));
        mlAttr.setNotShowCreateInEdit(getBoolean(resultSet, "notShowCreateInEdit"));
        mlAttr.setNotShowChooseInEdit(getBoolean(resultSet, "notShowChooseInEdit"));
        mlAttr.setNotShowEditInEdit(getBoolean(resultSet, "notShowEditInEdit"));
        mlAttr.setNotShowDeleteInEdit(getBoolean(resultSet, "notShowDeleteInEdit"));
        mlAttr.setNewLine(getBoolean(resultSet, "newLine"));
        mlAttr.setViewPos(getLong(resultSet, "viewPos"));
        mlAttr.setOffset(getLong(resultSet, "offset"));
        mlAttr.setTotalLength(getLong(resultSet, "totalLength"));
        mlAttr.setTitleLength(getLong(resultSet, "titleLength"));
        mlAttr.setTableHeight(getLong(resultSet, "tableHeight"));
        mlAttr.setMandatory(getBoolean(resultSet, "mandatory"));
        mlAttr.setInputmask(getString(resultSet, "inputmask"));
        mlAttr.setReadOnly(getBoolean(resultSet, "readOnly"));
        mlAttr.setTemplateView(getString(resultSet, "templateView"));
        mlAttr.setTemplateEdit(getString(resultSet, "templateEdit"));
        mlAttr.setTemplateCreate(getString(resultSet, "templateCreate"));
        mlAttr.setPrimaryKey(getBoolean(resultSet, "primaryKey"));
        mlAttr.setAutoIncrement(getBoolean(resultSet, "autoIncrement"));
        mlAttr.setLinkAttr(getLong(resultSet, "linkAttr"));
        mlAttr.setMlClass(getLong(resultSet, "mlClass"));
        mlAttr.setLinkClass(getLong(resultSet, "linkClass"));
        mlAttr.setDefaultSqlValue(getString(resultSet, "defaultSqlValue"));
        mlAttr.setUseInSimpleSearch(getBoolean(resultSet, "useInSimpleSearch"));
        mlAttr.setUseInExtendedSearch(getBoolean(resultSet, "useInExtendedSearch"));
        mlAttr.setSystemField(getBoolean(resultSet, "systemField"));

        mlAttr.setFieldTypeName(getFieldTypeName(mlAttr.getFieldType()));
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
