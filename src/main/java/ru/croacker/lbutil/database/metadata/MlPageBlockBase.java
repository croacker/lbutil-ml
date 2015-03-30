package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlPageBlockBase extends MlUnit {

    public static final String QUE = "select * from \"" + MlPageBlockBase.class.getSimpleName() + "\"";

  public String getDescription() {
    return String.valueOf(get("description"));
  }

  public Long getOrderNum() {
    return (Long) get("orderNum");
  }

  public String getZone() {
    return (String) get("zone");
  }

  public String getRealDynamicEntity() {
    return (String) get("realDynamicEntity");
  }

  public String getControllerJavaClass() {
    return (String) get("controllerJavaClass");
  }

  public String getBootJs() {
    return (String) get("bootJs");
  }

  public Long getPage() {
    return (Long) get("page");
  }

  public String getGuid() {
    return (String) get("guid");
  }

  public String getLastChange() {
    return (String) get("lastChange");
  }

  public String getTemplate() {
    return (String) get("template");
  }
}
