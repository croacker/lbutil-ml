package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlFolderAccess extends MlUnit {

    public static final String QUE = "select * from \"" + MlFolderAccess.class.getSimpleName() + "\"";

  public String getName(){
    return (String) get("name");
  }

  public String getGuid(){
    return (String) get("guid");
  }

  public String getLastChange(){
    return (String) get("lastChange");
  }

}
