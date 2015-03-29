package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlEnum extends MlUnit {

  public String getCode(){
    return (String) get("code");
  }

  public String getTitle(){
    return (String) get("title");
  }

  public String getDescription(){
    return (String) get("description");
  }

  public Long getMlAttr(){
    return (Long) get("mlAttr");
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

  public Long getMlAttr_order(){
    return (Long) get("mlAttr_order");
  }

}
