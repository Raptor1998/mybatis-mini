package com.raptor.mybatis.reflection;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ReflectionUtil {


    //TODO 结果集反射成对象失败了
    public static void setProTOBeanFromResult(Object entity, ResultSet resultSet) throws SQLException {
        System.out.println("resultSet反射成对象");
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < columnCount; i++) {

            String columnName = rsmd.getColumnName(i + 1).replace("_", "").toUpperCase();
            for (int j = 0; j < declaredFields.length; j++) {
                String filedName = declaredFields[j].getName().toUpperCase();
                if (columnName.equalsIgnoreCase(filedName)) {
                    if (declaredFields[j].getType().getSimpleName().equals("Integer")) {
                        setProTOBeanFromResult(entity, declaredFields[j].getName(), resultSet.getInt(rsmd.getColumnName(i + 1)));
                    } else if (declaredFields[j].getType().getSimpleName().equals("String")) {
                        setProTOBeanFromResult(entity, declaredFields[j].getName(), resultSet.getString(rsmd.getColumnName(i + 1)));
                    }
                    break;
                }
            }
        }
    }

    private static void setProTOBeanFromResult(Object entity, String name, Object value) {
        try {
            Field field = entity.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(entity, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
