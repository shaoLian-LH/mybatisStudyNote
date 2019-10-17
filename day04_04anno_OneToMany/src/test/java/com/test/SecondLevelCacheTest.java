package com.test;

import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class SecondLevelCacheTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;
    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfigure.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession(true);
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void destory() throws Exception{
        sqlSession.close();
        in.close();
    }
    @Test
    public void testFindById(){
        User user = userDao.findById(12);
        System.out.println(user);

        sqlSession.close();//释放掉一级缓存
        sqlSession = factory.openSession(true);

        IUserDao userDao1 = sqlSession.getMapper(IUserDao.class);
        User user1 = userDao1.findById(12);
        System.out.println(user1);
    }
}
