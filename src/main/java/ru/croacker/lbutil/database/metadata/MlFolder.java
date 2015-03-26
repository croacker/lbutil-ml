package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
public class MlFolder extends MlUnit {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Long childClass;

    @Getter
    @Setter
    private Long parent;

    @Getter
    @Setter
    private String childClassCondition;

    @Getter
    @Setter
    private String icon;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    private Date lastChange;

    @Getter
    @Setter
    private Long order;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private String iconURL;

}
