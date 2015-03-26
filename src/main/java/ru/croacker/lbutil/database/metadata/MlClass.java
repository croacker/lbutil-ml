package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MlClass extends MlUnit {

  @Getter @Setter
  private String entityName;
  @Getter @Setter
  private String tableName;
  @Getter @Setter
  private Boolean isSystem;
  @Getter @Setter
  private String javaClass;
  @Getter @Setter
  private Boolean hasHistory;
  @Getter @Setter
  private String description;
  @Getter @Setter
  private Long parent;
  @Getter @Setter
  private Boolean isAbstract;
  @Getter @Setter
  private Boolean isCacheable;
  @Getter @Setter
  private String titleFormat;
  @Getter @Setter
  private String handlerClassName;
  @Getter @Setter
  private String replicationHandlerClassName;

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
