package ru.croacker.lbutil.database.metadata;

import java.util.Date;

/**
 *
 */
public class MlScheduled extends MlUnit {

  public String getName(){
    return (String) get("name");
  }

  public String getCron(){
    return (String) get("cron");
  }

  public String getJobClass(){
    return (String) get("jobClass");
  }

  public String getLastResult(){
    return (String) get("lastResult");
  }

  public Date getLastStart(){
    return (Date) get("lastStart");
  }

  public Boolean getIsActive(){
    return (Boolean) get("isActive");
  }

  public String getGuid(){
    return (String) get("guid");
  }

  public String getLastChange(){
    return (String) get("lastChange");
  }

  public Boolean getRealDynamicEntity(){
    return (Boolean) get("realDynamicEntity");
  }

}
