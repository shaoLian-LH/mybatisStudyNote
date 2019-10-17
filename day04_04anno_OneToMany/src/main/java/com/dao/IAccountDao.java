package com.dao;

import com.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有账户并且获取每个账户所属的用户信息
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap",value={
        @Result(id = true,column = "id",property = "id"),
        @Result(column = "uid",property = "uid"),
        @Result(column = "money",property = "money"),
        @Result(property = "user",column = "uid",
        one = @One(select="com.dao.IUserDao.findById",fetchType= FetchType.EAGER))
    })
    List<Account> findAll();
    @Select("select * from account where uid=#{userId}")
    @Results(value={
            @Result(id = true,column = "aid",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money")
    })
    List<Account> findAccountByUid(Integer uid);
}
