package com.raptor.mybatis.bind;

import com.raptor.mybatis.sqlsession.SqlSession;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author raptor
 * @description MapperProxy
 * @date 2022/4/27 15:19
 */
public class MapperProxy {
    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Object invoke(Object proxt, Method method, Object[] args) {
        //最终还是将执行方法转给sqlsession，因为sqlsessio中封装了executor
        //根据调用的方法和类名以及参数，传给sqlsession对应的方法
        return null;
    }

}
