package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlFolder extends MlUnit {

  public String getTitle(){
    return (String) get("title");
  }

  public Long getChildClass(){
    return (Long) get("childClass");
  }

  public Long getParent(){
    return (Long) get("parent");
  }

  public String getChildClassCondition(){
    return (String) get("childClassCondition");
  }

  public String getIconn(){
    return (String) get("icon");
  }

  public String getGuid(){
    return (String) get("guid");
  }

  public String getLastChange(){
    return (String) get("lastChange");
  }

  public Long getOrder(){
    return (Long) get("order");
  }

  public String getUrl(){
    return (String) get("url");
  }

  public String getIconURL(){
    return (String) get("iconURL");
  }

}
