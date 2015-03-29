package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlRole extends MlUnit {

  public String getName(){
    return (String) get("name");
  }

  public String getGuid(){
    return (String) get("guid");
  }

  public String getLastChange(){
    return (String) get("lastChange");
  }

  public String getRoleType(){
    return (String) get("roleType");
  }

}
