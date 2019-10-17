package com.dao;

import com.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户，同时获取用户下所有用户账户的信息
     * @return
     */
    List<User> findAll();

    /**
     * 指定查询
     * @return
     */
    User findById(Integer userId);
    /**
     * 根据名称模糊查询
     */

}
