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

import java.io.IOException;
import java.io.InputStream;

public class SecondLevelCacheTest {
    private InputStream in;
    private SqlSessionFactory factory;
    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建一个SqlSeesionFactory
        /*将文件解析交给mybatis*/
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
    }
    @After //用于在测试方法执行之后执行
    public void closeAll() throws IOException{
        //6.释放资源

        in.close();
    }
    @Test
    public void testSecondLevelCache(){
        SqlSession sqlSession1 = factory.openSession(true);
        IUserDao dao1 = sqlSession1.getMapper(IUserDao.class);
        User user1 = dao1.findById(1);
        System.out.println(user1);

        sqlSession1.close();//一级缓存消失

        SqlSession sqlSession2 = factory.openSession(true);
        IUserDao dao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = dao2.findById(1);
        System.out.println(user2);

        sqlSession2.close();

        System.out.println("结果"+(user1 == user2));
    }
}
