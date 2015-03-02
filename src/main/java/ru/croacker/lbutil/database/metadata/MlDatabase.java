package ru.croacker.lbutil.database.metadata;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MlDatabase {

  @Getter
  private List<MlTable> tables = new ArrayList<>();

  @Getter
  private boolean isOriginal;

  public MlDatabase(boolean isOriginal) {
    this.isOriginal = isOriginal;
  }

  public void addTable(MlTable mlTable){
    getTables().add(mlTable);
  }

}
