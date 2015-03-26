package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * TODO заполнение
 */
public class MlAttrGroup extends MlUnit {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Long linkedClass;

    @Getter
    @Setter
    private Long parent;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    private Date lastChange;

    @Getter
    @Setter
    private Long linkedClass_order;

}
