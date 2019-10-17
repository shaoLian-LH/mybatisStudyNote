package com.dao;

import com.domain.QueryVo;
import com.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();

    /**
     * 保存用户方法
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);
    /**
     * 删除用户
     */
    void deleteUser(Integer userId);
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
     * 查询总用户数
     * @return
     */
    int findTotal();
    /**
     * 实体类对象包装查询
     */
    List<User> findUserByVO(QueryVo vo);
}
