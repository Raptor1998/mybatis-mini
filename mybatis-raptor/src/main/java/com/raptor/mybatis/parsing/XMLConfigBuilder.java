package com.raptor.mybatis.parsing;

import com.raptor.mybatis.mapping.MyConfiguration;
import com.raptor.mybatis.mapping.MyEnvironment;
import com.raptor.mybatis.mapping.MyMapperStatement;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class XMLConfigBuilder {

    public XMLConfigBuilder(InputStream inputStream) {
        System.out.println("假装在解析XML配置文件......");
    }

    public MyConfiguration parse() {
        final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/testdata?useUnicode=true";
        final String username = "root";
        final String password = "123456";
        MyEnvironment myEnvironment = new MyEnvironment(driver, url, username, password);

        Map<String, MyMapperStatement> mapperStatementMap = new HashMap<>();

        final String namespace = "com.raptor.dao.UserMapper";
        final String id = "selectUser";
        final String parameterType = "java.lang.Integer";
        final String resultType = "com.raptor.bean.User";


        //正常的sql
//        final String sql = "select * from user where id = #{id}";
        //为了简单  直接将sql更改为预编译sql
        final String sql = "select * from user where id = ?";
        //final String sql = "select * from user where id = ? and nickname = ?";


        MyMapperStatement myMapperStatement = new MyMapperStatement(namespace, id, parameterType, resultType, sql);

        mapperStatementMap.put(namespace + "." + id, myMapperStatement);
        return new MyConfiguration(myEnvironment, mapperStatementMap);
    }
}
