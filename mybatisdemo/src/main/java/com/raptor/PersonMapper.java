package com.raptor;

import org.apache.ibatis.annotations.Param;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * @author raptor
 * @description PersonMapper
 * @date 2022/4/27 14:43
 */
public interface PersonMapper {
    Person selectPerson(@Param("id") int id);
}
