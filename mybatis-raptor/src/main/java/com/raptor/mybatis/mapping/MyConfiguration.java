package com.raptor.mybatis.mapping;


import lombok.Data;
import java.util.Map;

@Data
public class MyConfiguration {
    //mybatis-config.xml
    private MyEnvironment myEnvironment;

    //xxMapper.xml
    private Map<String, MyMapperStatement> mapperStatementMap;

    public MyConfiguration(MyEnvironment myEnvironment, Map<String, MyMapperStatement> mapperStatementMap) {
        this.myEnvironment = myEnvironment;
        this.mapperStatementMap = mapperStatementMap;
    }
}
