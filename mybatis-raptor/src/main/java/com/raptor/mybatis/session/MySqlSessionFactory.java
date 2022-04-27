package com.raptor.mybatis.session;

import com.raptor.mybatis.mapping.MyConfiguration;

import java.util.concurrent.ConcurrentHashMap;

public class MySqlSessionFactory {

    private MyConfiguration myConfiguration;

    public MySqlSessionFactory() {
    }

    public MySqlSessionFactory(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    public MySqlSession openSession() {
        MyExecutor myExecutor = new MyExecutor(myConfiguration);
        return new MySqlSession(myConfiguration,myExecutor);
    }
}
