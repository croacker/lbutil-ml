package ru.croacker.lbutil.database.metadata;

/**
 *
 */
public class MlSecuritySettings extends MlUnit {

    public static final String QUE = "select * from \"" + MlSecuritySettings.class.getSimpleName() + "\"";

  public String getAuthType(){
    return (String) get("authType");
  }

  public Long getPasswordStrengthLength(){
    return (Long) get("passwordStrengthLength");
  }

  public String getPasswordStrengthAlphabet(){
    return (String) get("passwordStrengthAlphabet");
  }

  public Long getFailAuthCount(){
    return (Long) get("failAuthCount");
  }

  public Long getBlockTimeAfterFailAuth(){
    return (Long) get("blockTimeAfterFailAuth");
  }

  public Long getBlockAfterNotUse(){
    return (Long) get("blockAfterNotUse");
  }

  public Long getSessionLifeTime(){
    return (Long) get("sessionLifeTime");
  }

  public Long getPasswordLifeTime(){
    return (Long) get("passwordLifeTime");
  }

  public Long getForAdmin(){
    return (Long) get("forAdmin");
  }

  public String getGuid(){
    return (String) get("guid");
  }

  public String getLastChange(){
    return (String) get("lastChange");
  }

}
