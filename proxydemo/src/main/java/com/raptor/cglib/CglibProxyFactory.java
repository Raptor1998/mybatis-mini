package com.raptor.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author raptor
 * @description CglibProxyFactory
 * @date 2022/4/27 17:02
 */
public class CglibProxyFactory {
    public static Object getProxy(Class<?> clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new StudentInterceptor());
        // 创建代理类
        return enhancer.create();
    }
}
