package com.raptor.dao;

import com.raptor.bean.User;

/**
 * @author raptor
 * @description UserMapper
 * @date 2022/4/27 17:30
 */
public interface UserMapper {
    User selectUser(int id);
}
