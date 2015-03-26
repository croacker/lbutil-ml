package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
public class MlPage extends MlUnit  {

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String template;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    private Date lastChange;

    @Getter
    @Setter
    private String projectTemplate;

}
