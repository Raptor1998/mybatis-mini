package com.raptor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author raptor
 * @description JDBCTest
 * @date 2022/4/27 14:29
 */
public class JDBCTest {

    public static void createTable() throws ClassNotFoundException, SQLException {
        //1.加载数据库驱动（可以省略的，在javaEE的WEB项目中，jar包不可以省略。）
        //mysql6以下驱动：com.mysql.jdbc.Driver
        //mysql6及以上驱动：com.mysql.cj.jdbc.Driver
        Class.forName("com.mysql.jdbc.Driver");
        //2.通过驱动管理器DriverManager，获取JDBC的连接对象
        // 数据库连接地址格式
        //  主协议：子协议：//ip地址：端口号/数据库名称
        //数据库连接地址mysql写法：
        //jdbc:mysql://localhost:3306/test
        //连接地址oracle写法：
        //jdbc:oracle:thin:@ip地址:1521/ORCL
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdata","root","123456");
        //3.通过连接对象，创建SQL执行对象 (SQL执行环境)
        Statement state = conn.createStatement();
        //4.通过SQL执行对象 ,执行SQL语句.
        state.execute("create table person(id int, nickname varchar(32))");
        //5.释放连接
        state.close();	//释放执行对象
        conn.close();	//释放连接对象

    }
    //一下在添加数据库连接地址时，?后面那一串指定了插入数据的编码方式。
    public static void insertData() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdata?useUnicode=true&characterEncoding=utf-8","root","123456");
        Statement state=conn.createStatement();
        state.execute("insert into person values(1,'张三')");
        state.close();
        conn.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //createTable();
        insertData();
    }

}
