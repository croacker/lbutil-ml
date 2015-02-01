package ru.croacker.lbutil.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Данные о соединении которое будет сохраняться
 */
public class ConnectionDto {

    @Getter @Setter
    private String jdbcDriver;
    @Getter @Setter
    private String url;
    @Getter @Setter
    private String user;
    @Getter @Setter
    private String password;

}
