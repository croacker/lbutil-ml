package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlNavigationBlock extends MlUnit  {

    public static final String QUE = "select * from \"" + MlNavigationBlock.class.getSimpleName() + "\"";

  public Long getParentMlAttr(){
    return (Long) get("parentMlAttr");
  }

}
