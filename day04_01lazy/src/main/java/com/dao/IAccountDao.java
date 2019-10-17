package com.dao;

import com.domain.Account;

import java.util.List;

public interface IAccountDao {
    /*查询所有账户*/
    /*同时获取当前账户的所属用户信息*/
    List<Account> findAll();

    List<Account> findAccountByUid(Integer uid);
}
