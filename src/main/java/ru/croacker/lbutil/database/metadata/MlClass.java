package ru.croacker.lbutil.database.metadata;

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

  public String getEntityName(){
    return String.valueOf(get("entityName"));
  }

  public String getTableName(){
    return String.valueOf(get("tableName"));
  }

  public Boolean getIsSystem(){
    return (Boolean) get("isSystem");
  }

  public String getJavaClass(){
    return String.valueOf(get("javaClass"));
  }

  public Boolean getHasHistory(){
    return (Boolean) get("hasHistory");
  }

  public String getDescription(){
    return String.valueOf(get("description"));
  }

  public Long getParent(){
    return (Long) get("parent");
  }

  public Boolean getIsAbstract(){
    return (Boolean) get("isAbstract");
  }

  public Boolean getIsCacheable(){
    return (Boolean) get("isCacheable");
  }

  public String getTitleFormat(){
    return String.valueOf(get("titleFormat"));
  }

  public String getHandlerClassName(){
    return String.valueOf(get("handlerClassName"));
  }

  public String getReplicationHandlerClassName(){
    return String.valueOf(get("replicationHandlerClassName"));
  }

  public void setTableName(String value) {
    set("tableName", value);
  }

  public void setEntityName(String value) {
    set("entityName", value);
  }

  public void setIsSystem(boolean value) {
    set("isSystem", value);
  }

  public void setJavaClass(Object value) {
    set("javaClass", value);
  }

  public void setHasHistory(boolean value) {
    set("hasHistory", value);
  }

  public void setDescription(String value) {
    set("description", value);
  }

  public void setParent(Object value) {
    set("parent", value);
  }

  public void setIsAbstract(boolean value) {
    set("isAbstract", value);
  }

  public void setIsCacheable(boolean value) {
    set("isCacheable", value);
  }

  public void setTitleFormat(String value) {
    set("titleFormat", value);
  }

  public void setHandlerClassName(Object value) {
    set("handlerClassName", value);
  }

  public void setReplicationHandlerClassName(Object value) {
    set("replicationHandlerClassName", value);
  }
}