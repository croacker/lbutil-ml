package ru.croacker.lbutil.database.metadata;

import lombok.Getter;
import lombok.Setter;

/**
 *
 */
public class MlAttr extends MlUnit {

    //Строковое представление имени типа (String, Long и.т.д.)
    @Getter
    @Setter
    private String fieldTypeName;

}
