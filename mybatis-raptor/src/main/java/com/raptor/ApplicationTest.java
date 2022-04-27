package com.raptor;

import com.raptor.bean.User;
import com.raptor.dao.UserMapper;
import com.raptor.mybatis.session.MySqlSession;
import com.raptor.mybatis.session.MySqlSessionFactory;
import com.raptor.mybatis.session.MySqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class ApplicationTest {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        //1.读取配置文件
        InputStream inputStream = ApplicationTest.class.getClassLoader().getResourceAsStream(resource);

        MySqlSessionFactory mySqlSessionFactory = new MySqlSessionFactoryBuilder().build(inputStream);

        //获取sqlsession   相当于传统的jdbc的connection
        MySqlSession mySqlSession = mySqlSessionFactory.openSession();
        UserMapper userMapper = mySqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectUser(1);
        System.out.println(user);
//
//        sqlSession.commit();
//        sqlSession.flushStatements();
//        sqlSession.close();
    }
}
