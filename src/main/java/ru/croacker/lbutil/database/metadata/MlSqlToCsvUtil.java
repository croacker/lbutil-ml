package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

/**
 *
 */
public class MlSqlToCsvUtil extends MlUnit {

    @Getter
    @Setter
    private String sql;

    @Getter
    @Setter
    private String fileEncoding;

    @Getter
    @Setter
    private String columnSeparator;

    @Getter
    @Setter
    private String fileName;

}
