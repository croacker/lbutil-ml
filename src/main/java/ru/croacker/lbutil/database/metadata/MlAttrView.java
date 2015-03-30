package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlAttrView extends MlUnit {

    public static final String QUE = "select * from \"" + MlAttrView.class.getSimpleName() + "\"";

  public String getTitle(){
    return (String) get("title");
  }

  public String getCode(){
    return (String) get("code");
  }

  public String getTemplateName(){
    return (String) get("templateName");
  }

  public String getAttrType(){
    return (String) get("attrType");
  }

  public String getGuid(){
    return (String) get("guid");
  }

  public String getLastChange(){
    return (String) get("lastChange");
  }

}
