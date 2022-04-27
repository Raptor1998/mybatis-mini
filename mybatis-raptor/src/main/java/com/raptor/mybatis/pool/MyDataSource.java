package com.raptor.mybatis.pool;

import com.raptor.mybatis.mapping.MyEnvironment;
import com.raptor.mybatis.session.MyExecutor;
import jdk.internal.dynalink.linker.LinkerServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyDataSource implements MyDataSourceInterface {

    private MyEnvironment myEnvironment;
    private List<Connection> pool;
    private Connection conn = null;
    private static MyDataSource instance = null;
    private static final int POOL_SIZE = 15;

    public MyDataSource(MyEnvironment myEnvironment) {
        this.myEnvironment = myEnvironment;
        pool = new ArrayList<>(POOL_SIZE);
        this.createConnection();
    }

    public static MyDataSource getInstance(MyEnvironment myEnvironment) {
        if (instance == null) {
            instance = new MyDataSource(myEnvironment);
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        if (pool.size() > 0) {
            Connection connection = pool.get(0);
            pool.remove(connection);
            return connection;
        } else {
            return null;
        }
    }

    public void createConnection() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Class.forName(myEnvironment.getDriver());
                conn = DriverManager.getConnection(myEnvironment.getUrl(), myEnvironment.getUsername(), myEnvironment.getPassword());
                pool.add(conn);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void release(Connection conn){
        pool.add(conn);
    }

    public synchronized void closePool(){
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                conn = pool.get(i);
                conn.close();
                pool.remove(i);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
