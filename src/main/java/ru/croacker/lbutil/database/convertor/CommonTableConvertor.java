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
        MlClass mlClass = new MlClass();
        mlClass.setTableName(table.getName());
        mlClass.setEntityName(table.getName());
        mlClass.setIsSystem(false);
        mlClass.setJavaClass(null);
        mlClass.setHasHistory(false);
        mlClass.setDescription(getDescription(table));
        mlClass.setParent(null);
        mlClass.setIsAbstract(false);
        mlClass.setIsCacheable(false);
        mlClass.setTitleFormat("");
        mlClass.setHandlerClassName(null);
        mlClass.setReplicationHandlerClassName(null);

        for (Column column: table.getColumns()){
            mlClass.getColumns().add(columnConvertor.toMetadata(column));
        }

        return mlClass;
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
