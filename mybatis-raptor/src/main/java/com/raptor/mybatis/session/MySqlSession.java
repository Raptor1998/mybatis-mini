package com.raptor.mybatis.session;

import com.raptor.dao.UserMapper;
import com.raptor.mybatis.mapping.MyConfiguration;
import com.raptor.mybatis.mapping.MyMapperStatement;
import com.raptor.mybatis.proxy.ProxyMapper;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

@Data
public class MySqlSession {

    private MyExecutor myExecutor;

    private MyConfiguration myConfiguration;

    public MySqlSession(MyConfiguration myConfiguration, MyExecutor myExecutor) {
        this.myExecutor = myExecutor;
        this.myConfiguration = myConfiguration;
    }

    public <T> T getMapper(Class<T> clazz) {
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz},new ProxyMapper(this));
        return (T) o;
    }

    public <T> T selectOne(String statementKey, Object args){
//        System.out.println(myConfiguration.getMapperStatementMap().containsKey(statementKey));
        MyMapperStatement myMapperStatement = myConfiguration.getMapperStatementMap().get(statementKey);

        List<T> resultList =  myExecutor.query(myMapperStatement,args);
        if(resultList.size() > 1 ){
            throw new RuntimeException("more than one result !");
        }else if (resultList.size() == 0 ){
            throw new RuntimeException("empty set !");
        }else {
            System.out.println("结果集："+resultList.size());
            return resultList.get(0);
        }
    }

    public <T> T selectList(Object[] args) {
        return null;
    }

    public <T> T selectLMap(Object[] args) {
        return null;
    }
}
