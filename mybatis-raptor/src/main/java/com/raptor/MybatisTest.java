package com.raptor;

import com.raptor.bean.User;
import com.raptor.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author raptor
 * @description MybatisTest
 * @date 2022/4/27 14:44
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlsession   相当于传统的jdbc的connection
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper personMapper = sqlSession.getMapper(UserMapper.class);
        User person = personMapper.selectUser(1);
        System.out.println(person);

        sqlSession.commit();
        sqlSession.flushStatements();
        sqlSession.close();
    }
}
