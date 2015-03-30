package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlStaticPageBlock extends MlUnit {

    public static final String QUE = "select * from \"" + MlStaticPageBlock.class.getSimpleName() + "\"";

  public String getTemplate(){
    return (String) get("template");
  }

}
