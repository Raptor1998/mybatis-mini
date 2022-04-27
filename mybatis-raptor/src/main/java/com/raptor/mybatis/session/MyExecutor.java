package com.raptor.mybatis.session;

import com.raptor.mybatis.mapping.MyConfiguration;
import com.raptor.mybatis.mapping.MyMapperStatement;
import com.raptor.mybatis.pool.MyDataSource;
import com.raptor.mybatis.reflection.ReflectionUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyExecutor {

    private DataSource dataSource;

    public MyExecutor(MyConfiguration myConfiguration) {
        dataSource = MyDataSource.getInstance(myConfiguration.getMyEnvironment());
    }

    public <T> List<T> query(MyMapperStatement myMapperStatement, Object params) {
        List<T> resultList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            System.out.println(myMapperStatement.getSql());
            preparedStatement = connection.prepareStatement(myMapperStatement.getSql());
            if (params instanceof Integer) {
                preparedStatement.setInt(1, (Integer) params);
            } else if (params instanceof Long) {
                preparedStatement.setLong(1, (Long) params);
            } else if (params instanceof Double) {
                preparedStatement.setDouble(1, (Double) params);
            } else if (params instanceof String) {
                preparedStatement.setString(1, (String) params);
            }

            resultSet = preparedStatement.executeQuery();
//            System.out.println("查询的实际结果start-------------");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getInt("id"));
//                System.out.println(resultSet.getString("nickname"));
//            }
//            System.out.println("查询的实际结果end-------------");
            handleResultSet(resultSet,resultList,myMapperStatement.getResultType());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    //数据库查询结果封装到对象
    private <T> void handleResultSet(ResultSet resultSet, List<T> resultList, String resultType) {
        try {
            Class<?> aClass = Class.forName(resultType);

            while (resultSet.next()) {
                Object entity = aClass.newInstance();
                //吧从数据库查询出来的结果集字段设置到entity对象中去
                //TODO
                ReflectionUtil.setProTOBeanFromResult(entity,resultSet);

                System.out.println("反射的结果:"+entity);
                resultList.add((T)entity);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
