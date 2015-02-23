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

  public void addTable(MlTable mlTable){
    getTables().add(mlTable);
  }

}
