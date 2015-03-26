package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
public class MlEnum extends MlUnit {

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Long mlAttr;

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
    private Long mlAttr_order;



}
