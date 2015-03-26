package ru.croacker.lbutil.database.convertor;

import org.apache.commons.lang.StringUtils;
import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croacker.lbutil.database.metadata.MlClass;

/**
 * Конвертор для таблицы
 */
@Service
public class CommonTableConvertor implements Convertor<Table, MlClass> {

    @Autowired
    private CommonColumnConvertor columnConvertor;

    @Override
    public MlClass toMetadata(Table table) {
        MlClass mlTable = new MlClass();
        mlTable.setTableName(table.getName());
        mlTable.setEntityName(table.getName());
        mlTable.setIsSystem(false);
        mlTable.setJavaClass(null);
        mlTable.setHasHistory(false);
        mlTable.setDescription(getDescription(table));
        mlTable.setParent(null);
        mlTable.setIsAbstract(false);
        mlTable.setIsCacheable(false);
        mlTable.setTitleFormat("");
        mlTable.setHandlerClassName(null);
        mlTable.setReplicationHandlerClassName(null);

        for (Column column: table.getColumns()){
            mlTable.getColumns().add(columnConvertor.toMetadata(column));
        }

        return mlTable;
    }

    @Override
    public Table toTable(MlClass metadata) {
        Table table = new Table();
        return table;
    }

    private String getDescription(Table table){
        return StringUtils.isEmpty(table.getDescription()) ? table.getName() : table.getDescription();
    }
}
