package com.test;

import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class AnnotationCRUD {
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
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for(User u:users){
            System.out.println("每一个用户的数据");
            System.out.println(u);
            System.out.println(u.getAccounts());
        }
    }
    @Test
    public void testFindById(){
        User user = userDao.findById(12);
        System.out.println(user);
    }
    @Test
    public void testFindByName(){
        List<User> users = userDao.findUserByName("%陈%");
        for(User u:users){
            System.out.println(u);
        }
    }

}
