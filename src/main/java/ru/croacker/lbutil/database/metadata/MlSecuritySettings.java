package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
public class MlSecuritySettings extends MlUnit {

    @Getter
    @Setter
    private String authType;

    @Getter
    @Setter
    private Long passwordStrengthLength;

    @Getter
    @Setter
    private String passwordStrengthAlphabet;

    @Getter
    @Setter
    private Long failAuthCount;

    @Getter
    @Setter
    private Long blockTimeAfterFailAuth;

    @Getter
    @Setter
    private Long blockAfterNotUse;

    @Getter
    @Setter
    private Long sessionLifeTime;

    @Getter
    @Setter
    private Long passwordLifeTime;

    @Getter
    @Setter
    private Boolean forAdmin;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    private Date lastChange;

}
