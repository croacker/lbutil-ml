package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MlClass extends MlUnit {
  private List<MlAttr> columns = new ArrayList<>();

  public List<MlAttr> getColumns() {
    return columns;
  }

  public void addColumn(MlAttr mlAttr){
    getColumns().add(mlAttr);
  }

  public void addColumns(List<MlAttr> mlAttrs){
    getColumns().addAll(mlAttrs);
  }
}
