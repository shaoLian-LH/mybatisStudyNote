package com.test;

import com.dao.IUserDao;
import com.domain.User;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import com.mybatis.io.Resources;
import com.mybatis.io.sqlsession.*;
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建一个SqlSeesionFactory
        /*将文件解析交给mybatis*/
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产Seesion对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User u : users){
            System.out.println(u);
        }
        //6.释放资源
        session.close();
        in.close();
    }
}
