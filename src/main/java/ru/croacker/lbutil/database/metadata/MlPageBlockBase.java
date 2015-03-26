package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 *
 */
public class MlPageBlockBase extends MlUnit {

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Long orderNum;

    @Getter
    @Setter
    private String zone;

    @Getter
    @Setter
    private String realDynamicEntity;

    @Getter
    @Setter
    private String controllerJavaClass;

    @Getter
    @Setter
    private String bootJs;

    @Getter
    @Setter
    private Long page;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    private Date lastChange;

    @Getter
    @Setter
    private String template;


}
