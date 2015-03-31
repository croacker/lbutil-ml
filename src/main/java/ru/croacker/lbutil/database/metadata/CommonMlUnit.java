package ru.croacker.lbutil.database.metadata;

import lombok.Setter;

/**
 * Общий класс для любой таблицы
 */
public class CommonMlUnit extends MlUnit {

    public static final String QUE = "select * from \"{0}\"";

    @Setter
    private String mlClassTableName;

    @Override
    public String getMlClassTableName(){
        return mlClassTableName;
    }

    @Override
    public Long getId(){
        return Long.valueOf(hashCode());
    }
}
