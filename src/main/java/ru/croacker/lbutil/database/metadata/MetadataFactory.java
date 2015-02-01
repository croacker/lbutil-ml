package ru.croacker.lbutil.database.metadata;

import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MetadataFactory {

  private static List<String> ignoringTables = new ArrayList<>();
  static {
    ignoringTables.add("databasechangelog");
    ignoringTables.add("databasechangeloglock");
  }

  private static List<String> ignoringColumns = new ArrayList<>();
  static {

  }

  public MlDatabase createMlDatabase(Database database) {
    MlDatabase mlDatabase = new MlDatabase();
    for (Table table: database.getTables()){
      if(ignoringTables.contains(table.getName())){
        continue;
      }
      MlTable mlTable = new MlTable();
      mlTable.setTableName(table.getName());
      mlTable.setEntityName(table.getName());
      for(Column column: table.getColumns()){
        if(ignoringColumns.contains(column.getName())){
          continue;
        }
        MlColumn mlColumn = new MlColumn();

        mlTable.getColumns().add(mlColumn);
      }

      mlDatabase.getTables().add(mlTable);
    }
    return mlDatabase;
  }

}
