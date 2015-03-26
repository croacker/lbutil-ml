package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
public class MlAttrView extends MlUnit {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String templateName;

    @Getter
    @Setter
    private String attrType;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    private Date lastChange;

}
