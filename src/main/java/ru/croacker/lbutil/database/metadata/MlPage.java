package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlPage extends MlUnit  {

    public static final String QUE = "select * from \"" + MlPage.class.getSimpleName() + "\"";

  public String getUrl(){
    return (String) get("url");
  }

  public String getDescription(){
    return String.valueOf(get("description"));
  }

  public String getTitle(){
    return (String) get("title");
  }

  public String getTemplate(){
    return (String) get("template");
  }

  public String getGuid(){
    return (String) get("guid");
  }

  public String getLastChange(){
    return (String) get("lastChange");
  }

  public String getProjectTemplate(){
    return (String) get("projectTemplate");
  }

}
