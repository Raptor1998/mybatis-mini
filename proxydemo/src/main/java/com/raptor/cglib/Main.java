package com.raptor.cglib;

/**
 * @author raptor
 * @description Main
 * @date 2022/4/27 17:02
 */
public class Main {
    public static void main(String[] args) {
        Student stu = (Student) CglibProxyFactory.getProxy(Student.class);
        stu.getStuName();
    }
}
