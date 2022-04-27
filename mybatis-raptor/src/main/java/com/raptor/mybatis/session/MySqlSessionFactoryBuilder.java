package com.raptor.mybatis.session;

import com.raptor.mybatis.mapping.MyConfiguration;
import com.raptor.mybatis.parsing.XMLConfigBuilder;

import java.io.InputStream;

public class MySqlSessionFactoryBuilder {

    public MySqlSessionFactory build(InputStream inputStream) {
        //mybatis的配置信息
        MyConfiguration myConfiguration = new XMLConfigBuilder(inputStream).parse();

        return new MySqlSessionFactory(myConfiguration);
    }
}
