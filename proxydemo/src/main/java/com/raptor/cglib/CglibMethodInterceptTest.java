package com.raptor.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author raptor
 * @description CglibMethodInterceptTest
 * @date 2022/4/27 16:58
 */
public class CglibMethodInterceptTest {
    public static void main(String[] args) {
        //创建一个Enhancer对象
        Enhancer enchaner = new Enhancer();
        //设置被代理的类
        enchaner.setSuperclass(Student.class);
        //创建一个回调接口
        Callback interceptor = new MethodInterceptor() {

            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                    throws Throwable {
                System.out.println("原方法名是 ： " + method.getName());
                System.out.println("原方法声明的类为 " + method.getDeclaringClass());
                System.out.println("我是 " + (String) proxy.invokeSuper(obj, args));
                System.out.println("调用结束");
                return null;
            }
        };
        enchaner.setCallback(interceptor);
        Student student = (Student) enchaner.create();
        student.getStuName();

    }

}
