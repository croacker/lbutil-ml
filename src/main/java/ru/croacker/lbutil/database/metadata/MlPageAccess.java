package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlPageAccess extends MlUnit {

  public String getName(){
    return (String) get("name");
  }

  public String getUrlRegexp(){
    return (String) get("urlRegexp");
  }

  public String getGuid(){
    return (String) get("guid");
  }

  public String getLastChange(){
    return (String) get("lastChange");
  }

}
