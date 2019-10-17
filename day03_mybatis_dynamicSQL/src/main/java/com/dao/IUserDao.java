package com.dao;

import com.domain.QueryVo;
import com.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();

    /**
     * 指定查询
     * @return
     */
    User findById(Integer userId);
    /**
     * 根据名称模糊查询
     */
    List<User> findByName(String username);

    /**
     * 根据传入的条件查询
     * 根据User查询条件，但是仅有部分的数据
     * @return
     */
    List<User> findUserByCondition(User user);
    /*
    * 利用QueryVo中提供的id集合查询用户信息
    * */
    List<User> finduserInIds(QueryVo vo);
}
