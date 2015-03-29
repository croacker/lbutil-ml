package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlNavigationBlock extends MlUnit  {

  public Long getParentMlAttr(){
    return (Long) get("parentMlAttr");
  }

}
