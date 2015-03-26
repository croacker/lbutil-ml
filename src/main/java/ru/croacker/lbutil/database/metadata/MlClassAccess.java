package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
public class MlClassAccess extends MlUnit {

    @Getter
    @Setter
    private Long mlClass;

    @Getter
    @Setter
    private Boolean create;

    @Getter
    @Setter
    private Boolean read;

    @Getter
    @Setter
    private Boolean update;

    @Getter
    @Setter
    private Boolean delete;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    private Date lastChange;

}
