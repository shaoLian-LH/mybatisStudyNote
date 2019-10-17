package com.test;

import com.dao.IUserDao;
import com.dao.impl.UserDaoImpl;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MybatisTest {
    private InputStream in;
    private IUserDao userDao;
    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建一个SqlSeesionFactory
        /*将文件解析交给mybatis*/
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.创建DAO对象
        userDao = new UserDaoImpl(factory);

     }
    @After //用于在测试方法执行之后执行
    public void closeAll() throws IOException{
        in.close();
    }

    @Test
    public void testFindAll() throws IOException {
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User u : users){
            System.out.println(u);
        }
    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave() throws IOException {
        User user = new User();
        user.setUsername("mybatis_saveUser_d");
        user.setAddress("闽江小监狱");
        user.setSex("女");
        user.setBirthday(new Date());
        //5.使用代理对象执行方法
        System.out.println("保存操作之前："+user);
        userDao.saveUser(user);
        System.out.println("保存操作之后："+user);
    }

    /**
     * 更新测试
     * @throws IOException
     */
    @Test
    public void updateTest() throws IOException {
        User user = new User();
        user.setId(14);
        user.setUsername("mybatis_updateUser2");
        user.setAddress("闽江监狱");
        user.setSex("女");
        user.setBirthday(new Date());
        //5.使用代理对象执行方法
        userDao.updateUser(user);
    }
    /**
     * 删除测试
     * @throws IOException
     */
    @Test
    public void deleteTest() throws IOException {
        userDao.deleteUser(14);
    }
    /**
     * 指定查询
     */
    @Test
    public void selectByIdTest() throws IOException {
        User user = userDao.findById(1);
        System.out.println(user);
    }
    /**
     * 模糊查询
     * 需要在这里提供百分号
     */
    @Test
    public void findByNameTest() throws IOException {
        List<User> users = userDao.findByName("%陈%");
        for(User u : users){
            System.out.println(u);
        }
    }
    /**
     * 查询总记录条数
     */
    @Test
    public void findTotalTest() throws IOException {
        int totalNum = userDao.findTotal();
        System.out.println("总条数："+totalNum);
    }

}
