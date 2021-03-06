package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlSqlToCsvUtil extends MlUnit {

    public static final String QUE = "select * from \"" + MlSqlToCsvUtil.class.getSimpleName() + "\"";

  public String getSql(){
    return (String) get("sql");
  }

  public String getFileEncoding(){
    return (String) get("fileEncoding");
  }

  public String getColumnSeparator(){
    return (String) get("columnSeparator");
  }

  public String getFileName(){
    return (String) get("fileName");
  }

}
