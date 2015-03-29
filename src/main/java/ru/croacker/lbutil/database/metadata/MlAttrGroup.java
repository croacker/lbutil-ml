package ru.croacker.lbutil.database.metadata;

/**
 * TODO заполнение
 */
public class MlAttrGroup extends MlUnit {

  public String getTitle(){
    return (String) get("title");
  }

  public Long getLinkedClass(){
    return (Long) get("linkedClass");
  }

  public Long getParent(){
    return (Long) get("parent");
  }

  public String getGuid(){
    return (String) get("guid");
  }

  public String getLastChange(){
    return (String) get("lastChange");
  }

  public String getLinkedClass_order(){
    return (String) get("linkedClass_order");
  }

}
