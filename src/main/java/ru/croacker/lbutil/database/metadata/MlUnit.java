package ru.croacker.lbutil.database.metadata;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

/**
 *
 */
public abstract class MlUnit {

    @Getter
    private Map<String, Object> columnValues = Maps.newHashMap();

    public String getMlClassTableName(){
        return getClass().getSimpleName();
    }

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
        set("id", value);
    }

    public Object get(String key){
        return getColumnValues().get(key);
    }

    public String getString(String key){
        return String.valueOf(getColumnValues().get(key));
    }

    public void set(String key, Object value){
        getColumnValues().put(key, value);
    }

}
