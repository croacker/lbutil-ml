package ru.croacker.lbutil.database.convertor;

import org.apache.commons.lang.StringUtils;
import org.apache.ddlutils.model.Column;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.metadata.MlColumn;

import java.sql.Timestamp;

/**
 * Конвертер для колонки
 */
@Service
public class CommonColumnConvertor implements Convertor<Column, MlColumn> {

    @Override
    public MlColumn toMetadata(Column column) {
        MlColumn mlColumn = new MlColumn();
        mlColumn.setTableFieldName(column.getName());
        mlColumn.setEntityFieldName(column.getName());
        mlColumn.setSystemField(false);
        mlColumn.setPrimaryKey(column.isPrimaryKey());
        mlColumn.setAutoIncrement(column.isAutoIncrement());
        mlColumn.setFieldType(getFieldType(column));
        mlColumn.setLinkAttr(null);
        mlColumn.setMlClass(null);
        mlColumn.setLinkClass(null);
        mlColumn.setLinkFilter(null);
        mlColumn.setInForm(true);
        mlColumn.setUseInSimpleSearch(true);
        mlColumn.setUseInExtendedSearch(true);
        mlColumn.setFieldFormat(null);
        mlColumn.setGroup(null);
        mlColumn.setDescription(getDescription(column));
        return mlColumn;
    }

    @Override
    public Column toTable(MlColumn mlColumn) {
        Column column = new Column();

        return column;
    }

    private String getFieldType(Column column) {
        return "STRING";
//                LONG(Long.class),
//                BOOLEAN(Boolean.class),
//                DATE(Timestamp.class),
    }

    private String getDescription(Column column){
        return StringUtils.isEmpty(column.getDescription()) ? column.getName() : column.getDescription();
    }

}
