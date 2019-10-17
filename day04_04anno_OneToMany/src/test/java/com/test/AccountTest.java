package com.test;

import com.dao.IAccountDao;
import com.dao.IUserDao;
import com.domain.Account;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IAccountDao accountDao;
    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfigure.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession(true);
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }
    @After
    public void destory() throws Exception{
        sqlSession.close();
        in.close();
    }
    @Test
    public void testFindAll(){
        List<Account> accounts = accountDao.findAll();
        for(Account a:accounts){
            System.out.println("每个账户的信息");
            System.out.println(a);
            System.out.println(a.getUser());
        }
    }
}
