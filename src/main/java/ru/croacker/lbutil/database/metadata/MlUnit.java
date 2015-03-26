package ru.croacker.lbutil.database.metadata;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 *
 */
public abstract class MlUnit {

    @Getter
    @Setter
    private Long id;

    @Getter
    private Map<String, Object> columnValues = Maps.newHashMap();

}
