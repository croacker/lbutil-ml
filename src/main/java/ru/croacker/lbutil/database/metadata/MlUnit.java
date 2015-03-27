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
    private Map<String, Object> columnValues = Maps.newHashMap();

    public static  <T extends MlUnit> T newInstance(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (InstantiationException|IllegalAccessException  e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public String getTypeName() {
        return getClass().getName();
    }

    public Long getId(){
        return (Long) getColumnValues().get("id");
    }

    public void setId(Long value){
        getColumnValues().put("id", value);
    }

    public Object get(String key){
        return getColumnValues().get(key);
    }

    public void set(String key, Object value){
        getColumnValues().put(key, value);
    }

}
