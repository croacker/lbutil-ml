package ru.croacker.lbutil.database.metadata;

import java.util.Date;

/**
 *
 */
public class MlUser extends MlUnit {

  public String getLogin(){
    return (String) get("login");
  }

  public String getPassword(){
    return (String) get("password");
  }

  public String getFirst_name(){
    return (String) get("first_name");
  }

  public String getLast_name(){
    return (String) get("last_name");
  }

  public String getGuid(){
    return (String) get("guid");
  }

  public String getLastChange(){
    return (String) get("lastChange");
  }

  public String getFailAuthCount(){
    return (String) get("failAuthCount");
  }

  public Date getLastLogin(){
    return (Date) get("lastLogin");
  }

  public Date getLastPasswordChange(){
    return (Date) get("lastPasswordChange");
  }

  public Boolean getIsBlocked(){
    return (Boolean) get("isBlocked");
  }

  public Boolean getIsTemporalBlocked(){
    return (Boolean) get("isTemporalBlocked");
  }

  public Date getTemporalBlockedBefore(){
    return (Date) get("temporalBlockedBefore");
  }

  public Boolean getNeedChangePassword(){
    return (Boolean) get("needChangePassword");
  }

  public Boolean getHomePage(){
    return (Boolean) get("homePage");
  }

  public Boolean getHolder(){
    return (Boolean) get("holder");
  }

}
