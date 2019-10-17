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
    public void testSave(){
        User user = new User();
        user.setUsername("mybatis_Annountation");
        user.setAddress("福建省福州市");
        user.setSex("女");
        user.setBirthday(new Date());
        userDao.saveUser(user);
    }
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(16);
        user.setUsername("mybatis_Annountation");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("闽江监狱");
        userDao.updateUser(user);
    }
    @Test
    public void testDelete(){
       userDao.deleteUser(16);
    }

    @Test
    public void testFindById(){
        User user = userDao.findById(12);
        System.out.println(user);
    }
    @Test
    public void testFindByName(){
        List<User> users = userDao.findUserByName("陈");
        for(User u:users){
            System.out.println(u);
        }
    }
    @Test
    public void testFindTotal(){
        System.out.println("总数"+userDao.findTotalUser());
    }
}
