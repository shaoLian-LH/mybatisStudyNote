package com.test;

import com.dao.IUserDao;
import com.domain.QueryVo;
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
    private SqlSession session;
    private IUserDao userDao;
    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建一个SqlSeesionFactory
        /*将文件解析交给mybatis*/
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产Seesion对象
        session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }
    @After //用于在测试方法执行之后执行
    public void closeAll() throws IOException{
        //6.释放资源
        session.close();
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
        user.setUsername("mybatis_saveUser_c");
        user.setAddress("闽江小监狱");
        user.setSex("女");
        user.setBirthday(new Date());
        //5.使用代理对象执行方法
        System.out.println("保存操作之前："+user);
        userDao.saveUser(user);
        //没有自动提交事务时只能手动提交
        session.commit();
        System.out.println("保存操作之后："+user);
    }

    /**
     * 更新测试
     * @throws IOException
     */
    @Test
    public void updateTest() throws IOException {
        User user = new User();
        user.setId(10);
        user.setUsername("mybatis_updateUser");
        user.setAddress("闽江监狱");
        user.setSex("女");
        user.setBirthday(new Date());
        //5.使用代理对象执行方法
        userDao.updateUser(user);
        //没有自动提交事务时只能手动提交
        session.commit();
    }
    /**
     * 删除测试
     * @throws IOException
     */
    @Test
    public void deleteTest() throws IOException {
        userDao.deleteUser(10);
        //没有自动提交事务时只能手动提交
        session.commit();
    }
    /**
     * 指定查询
     */
    @Test
    public void selectByIdTest() throws IOException {
        User user = userDao.findById(1);
        //没有自动提交事务时只能手动提交
        session.commit();
        System.out.println(user);
    }
    /**
     * 模糊查询
     * 需要在这里提供百分号
     */
    @Test
    public void findByNameTest() throws IOException {
        List<User> users = userDao.findByName("陈");
        //没有自动提交事务时只能手动提交
        session.commit();
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
        //没有自动提交事务时只能手动提交
        session.commit();
        System.out.println("总条数："+totalNum);
    }

    /**
     * QueryVo作为条件
     * @throws IOException
     */
    @Test
    public void findByVoTest() throws IOException {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%陈%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVO(vo);
        //没有自动提交事务时只能手动提交
        session.commit();
        for(User u : users){
            System.out.println(u);
        }
    }
}
