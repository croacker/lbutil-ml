package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlClassAccess extends MlUnit {

  public Long getMlClass(){
    return (Long) get("mlClass");
  }

  public Boolean getCreate(){
    return (Boolean) get("create");
  }

  public Boolean getRead(){
    return (Boolean) get("read");
  }

  public Boolean getUpdate(){
    return (Boolean) get("update");
  }

  public Boolean getDelete(){
    return (Boolean) get("delete");
  }

  public String getGuid(){
    return (String) get("guid");
  }

  public String getLastChange(){
    return (String) get("lastChange");
  }

}
