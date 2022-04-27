package com.raptor.mybatis.mapping;

import lombok.Data;

@Data
public class MyMapperStatement {
    private String namespace;
    private String id;
    private String parameterType;
    private String resultType;
    private String sql;

    public MyMapperStatement(String namespace, String id, String parameterType, String resultType, String sql) {
        this.namespace = namespace;
        this.id = id;
        this.parameterType = parameterType;
        this.resultType = resultType;
        this.sql = sql;
    }
}
