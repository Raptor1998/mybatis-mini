package com.raptor.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author raptor
 * @description Main2
 * @date 2022/4/27 16:43
 */
public class Main2 {
    public static void main(String[] args) {
        Sell vendor = new Vendor();
        Sell veproxy = (Sell) Proxy.newProxyInstance(Sell.class.getClassLoader(), vendor.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("sell")){
                    System.out.println("asdas");
                }
                return method.invoke(vendor,args);
            }
        });
        veproxy.ad();
        veproxy.sell();
    }
}
