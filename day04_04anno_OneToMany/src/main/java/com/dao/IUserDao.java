package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 在mybatis中，CRUD一共有四个注解
 * @SELECT，@DELETE，@UPDATE，@INSERT
 */
@CacheNamespace(blocking = true)
public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    @Select(value = "select * from user")
    @Results(id = "userMap",value = {
         @Result(id=true,column = "id",property = "userId"),
         @Result(column = "username",property = "userName"),
         @Result(column = "address",property = "userAddress"),
         @Result(column = "sex",property = "userSex"),
         @Result(column = "birthday",property = "userBirthday"),
         @Result(property = "accounts",column = "id",
         many = @Many(select = "com.dao.IAccountDao.findAccountByUid",fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 通过ID查找用户
     */
    @Select("select * from user where id = #{id}")
    @ResultMap(value = {"userMap"})
    User findById(Integer id);
    /**
     * 模糊查询
     */
    @Select("select * from user where username like #{username}")
    @ResultMap(value = {"userMap"})
    List<User> findUserByName(String username);

}
