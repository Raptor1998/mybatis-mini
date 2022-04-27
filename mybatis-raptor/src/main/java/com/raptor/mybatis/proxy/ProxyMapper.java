package com.raptor.mybatis.proxy;

import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;
import com.raptor.mybatis.session.MySqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collector;

public class ProxyMapper implements InvocationHandler {

    private MySqlSession mySqlSession;

    public ProxyMapper(MySqlSession mySqlSession) {
        this.mySqlSession = mySqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理mapper生成中......");
        //获取一下返回类型
        Class<?> clazz = method.getReturnType();

        if (Collector.class.isAssignableFrom(clazz)) {
            //表示返回的是list集合
            return mySqlSession.selectList(args);
        } else if (Map.class.isAssignableFrom(clazz)) {
            //返回的map集合
            return mySqlSession.selectLMap(args);
        } else {
            //返回对象类型
            String statementKey = method.getDeclaringClass().getName() + "." + method.getName();
            return mySqlSession.selectOne(statementKey, null == args ? null : args[0]);
        }

    }
}
