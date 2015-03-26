package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
public class MlPageAccess extends MlUnit {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String urlRegexp;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    private Date lastChange;

}
