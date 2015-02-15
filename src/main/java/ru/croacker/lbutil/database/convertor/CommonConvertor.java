package ru.croacker.lbutil.database.convertor;

import org.apache.ddlutils.model.Table;
import ru.croacker.lbutil.database.metadata.MlTable;

/**
 * Created by user on 16.02.2015.
 */
public class CommonConvertor implements Convertor<Table, MlTable> {

  @Override
  public MlTable toMetadata(Table table) {
    MlTable mlTable = new MlTable();

    return mlTable;
  }

  @Override
  public Table toTable(MlTable metadata) {
    Table table = new Table();
    return table;
  }
}
