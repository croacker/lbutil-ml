package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
public class MlUser extends MlUnit {

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String first_name;

    @Getter
    @Setter
    private String last_name;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    private Date lastChange;

    @Getter
    @Setter
    private Long failAuthCount;

    @Getter
    @Setter
    private Date lastLogin;

    @Getter
    @Setter
    private Date lastPasswordChange;

    @Getter
    @Setter
    private Boolean isBlocked;

    @Getter
    @Setter
    private Boolean isTemporalBlocked;

    @Getter
    @Setter
    private Date temporalBlockedBefore;

    @Getter
    @Setter
    private Boolean needChangePassword;

    @Getter
    @Setter
    private String homePage;

    @Getter
    @Setter
    private String holder;

}
