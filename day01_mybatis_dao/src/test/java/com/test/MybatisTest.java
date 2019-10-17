package com.test;

import com.dao.impl.UserDaoImpl;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
        //4.生成对应的dao实现类对象
        UserDaoImpl userDao = new UserDaoImpl(factory);

        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User u : users){
            System.out.println(u);
        }
        //6.释放资源
        in.close();
    }
}
