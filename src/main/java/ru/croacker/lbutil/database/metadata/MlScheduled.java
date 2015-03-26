package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
public class MlScheduled extends MlUnit {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String cron;

    @Getter
    @Setter
    private String jobClass;

    @Getter
    @Setter
    private String lastResult;

    @Getter
    @Setter
    private Date lastStart;

    @Getter
    @Setter
    private Boolean isActive;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    private Date lastChange;

    @Getter
    @Setter
    private String realDynamicEntity;

}
