package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
public class MlAttr extends MlUnit {

    public static final String QUE = "select * from \"" + MlAttr.class.getSimpleName() + "\"";

  public String getEntityFieldName(){
    return String.valueOf(get("entityFieldName"));
  }

  public String getTableFieldName(){
    return String.valueOf(get("tableFieldName"));
  }

  public Boolean getSystemField(){
    return (Boolean) get("systemField");
  }

  public Boolean getPrimaryKey(){
    return (Boolean) get("primaryKey");
  }

  public Boolean getAutoIncrement(){
    return (Boolean) get("autoIncrement");
  }

  public String getFieldType(){
    return (String) get("fieldType");
  }

  public Long getLinkAttr(){
    return (Long) get("linkAttr");
  }

  public Long getMlClass(){
    return (Long) get("mlClass");
  }

  public Long getLinkClass(){
    return (Long) get("linkClass");
  }

  public String getLinkFilter(){
    return (String) get("linkFilter");
  }

  public Boolean getInForm(){
    return (Boolean) get("inForm");
  }

  public Boolean getInList(){
    return (Boolean) get("inList");
  }

  public Boolean getUseInSimpleSearch(){
    return (Boolean) get("useInSimpleSearch");
  }

  public Boolean getUseInExtendedSearch(){
    return (Boolean) get("useInExtendedSearch");
  }

  public String getFieldFormat(){
    return (String) get("fieldFormat");
  }

  public Long getGroup(){
    return (Long) get("group");
  }

  public String getDescription(){
    return (String) get("description");
  }

  public Long getViewPos(){
    return (Long) get("viewPos");
  }

  public Boolean getNewLine(){
    return (Boolean) get("newLine");
  }

  public Long getOffset(){
    return (Long) get("offset");
  }

  public Long getTotalLength(){
    return (Long) get("totalLength");
  }

  public Long getTitleLength(){
    return (Long) get("titleLength");
  }

  public Long getTableHeight(){
    return (Long) get("tableHeight");
  }

  public Boolean getMandatory(){
    return (Boolean) get("mandatory");
  }

  public String getReplicationType(){
    return (String) get("replicationType");
  }

  public String getGuid(){
    return (String) get("guid");
  }

  public Date getLastChange(){
    return (Date) get("lastChange");
  }

//  public Date getLastChange(){
//    return (Date) get("lastChange");
//  }

  public String getDefaultValue(){
    return (String) get("defaultValue");
  }

  public Boolean getLazy(){
    return (Boolean) get("lazy");
  }

  public Long getView(){
    return (Long) get("view");
  }

  public String getTemplateView(){
    return (String) get("templateView");
  }

  public String getTemplateEdit(){
    return (String) get("templateEdit");
  }

  public String getTemplateCreate(){
    return (String) get("templateCreate");
  }

  public String getInputmask(){
    return (String) get("inputmask");
  }

  public String getDefaultSqlValue(){
    return (String) get("defaultSqlValue");
  }

  public Boolean getVirtual(){
    return (Boolean) get("virtual");
  }

  public String getLongLinkValue(){
    return (String) get("longLinkValue");
  }

  public Boolean getReadOnly(){
    return (Boolean) get("readOnly");
  }

  public Boolean getOrdered(){
    return (Boolean) get("ordered");
  }

  public Long getMlClass_order(){
    return (Long) get("mlClass_order");
  }

  public Boolean getNotShowCreate(){
    return (Boolean) get("notShowCreate");
  }

  public Boolean getNotShowDelete(){
    return (Boolean) get("notShowDelete");
  }

  public Boolean getNotShowChoose(){
    return (Boolean) get("notShowChoose");
  }

  public Boolean getNotShowEdit(){
    return (Boolean) get("notShowEdit");
  }

  public Boolean getNotShowCreateInEdit(){
    return (Boolean) get("notShowCreateInEdit");
  }

  public Boolean getNotShowEditInEdit(){
    return (Boolean) get("notShowEditInEdit");
  }

  public Boolean getNotShowChooseInEdit(){
    return (Boolean) get("notShowChooseInEdit");
  }

  public Boolean getNotShowDeleteInEdit(){
    return (Boolean) get("notShowDeleteInEdit");
  }

  public String getManyToManyTableName(){
    return (String) get("manyToManyTableName");
  }

  public String getManyToManyFieldNameM(){
    return (String) get("manyToManyFieldNameM");
  }

  public String getManyToManyFieldNameN(){
    return (String) get("manyToManyFieldNameN");
  }

  //Строковое представление имени типа (String, Long и.т.д.)
  @Getter @Setter
  private String fieldTypeName;

  public void setTableFieldName(String value) {
    set("tableFieldName", value);
  }

  public void setEntityFieldName(String value) {
    set("entityFieldName", value);
  }

  public void setSystemField(boolean value) {
    set("systemField", value);
  }

  public void setPrimaryKey(boolean value) {
    set("primaryKey", value);
  }

  public void setAutoIncrement(boolean value) {
    set("autoIncrement", value);
  }

  public void setFieldType(String value) {
    set("fieldTyp", value);
  }

  public void setLinkAttr(Object value) {
    set("linkAttr", value);
  }

  public void setMlClass(Object value) {
    set("mlClass", value);
  }

  public void setLinkClass(Object value) {
    set("linkClass", value);
  }

  public void setLinkFilter(Object value) {
    set("linkFilter", value);
  }

  public void setInForm(boolean value) {
    set("inForm", value);
  }

  public void setUseInSimpleSearch(boolean value) {
    set("useInSimpleSearch", value);
  }

  public void setUseInExtendedSearch(boolean value) {
    set("useInExtendedSearch", value);
  }

  public void setFieldFormat(Object value) {
    set("fieldFormat", value);
  }

  public void setGroup(Object value) {
    set("group", value);
  }

  public void setDescription(String value) {
    set("description", value);
  }

  public void setDefaultValue(String value) {
    set("defaultValue", value);
  }

  public void setVirtual(boolean value) {
    set("virtual", value);
  }

  public void setLongLinkValue(Object value) {
    set("longLinkValue", value);
  }

  public void setReadOnly(boolean value) {
    set("readOnly", value);
  }
}
