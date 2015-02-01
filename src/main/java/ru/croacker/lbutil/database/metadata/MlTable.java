package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MlTable {

  @Getter @Setter
  private Long id;
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

  private List<MlColumn> columns = new ArrayList<>();

  public List<MlColumn> getColumns() {
    return columns;
  }
}
