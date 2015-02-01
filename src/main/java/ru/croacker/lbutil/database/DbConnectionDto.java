package ru.croacker.lbutil.database;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

/**
 * Соединение с БД
 */
public class DbConnectionDto {

    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private String jdbcDriver;
    @Getter
    private String url;
    @Getter
    private String user;
    @Getter
    private String password;


    public DbConnectionDto setId(Long id){
        this.id = id;
        return this;
    }

    public DbConnectionDto setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
        return this;
    }

    public DbConnectionDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public DbConnectionDto setUser(String user) {
        this.user = user;
        return this;
    }

    public DbConnectionDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public DbConnectionDto setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString(){
        return "[" + getId() + "] " +
            (!StringUtils.isEmpty(getName()) ? getName() :
            (!StringUtils.isEmpty(getUrl()) ? getUrl() : getJdbcDriver()));
    }
}
