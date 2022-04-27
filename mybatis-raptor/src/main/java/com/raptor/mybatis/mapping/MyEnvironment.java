package com.raptor.mybatis.mapping;

import lombok.Data;

@Data
public class MyEnvironment {
    private String driver;
    private String url;
    private String username;
    private String password;

    public MyEnvironment(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
}
