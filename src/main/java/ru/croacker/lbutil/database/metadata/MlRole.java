package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
public class MlRole extends MlUnit {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    private Date lastChange;

    @Getter
    @Setter
    private String roleType;

}
