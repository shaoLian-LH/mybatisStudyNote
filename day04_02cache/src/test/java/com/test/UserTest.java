package com.test;

import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserTest {
    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;
    private SqlSessionFactory factory;
    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建一个SqlSeesionFactory
        /*将文件解析交给mybatis*/
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        //3.使用工厂生产Seesion对象
        session = factory.openSession(true);
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
    public void testFirstLevelCache(){
        User user1 = userDao.findById(1);
        System.out.println(user1);

        //再次获取新的sqlsession对象
/*
        session.close();
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
*/
        //清除缓存
        session.clearCache();

        User user2 = userDao.findById(1);
        System.out.println(user2);
        System.out.println("user1与user2是否相等？"+(user1 == user2));

    }
}
