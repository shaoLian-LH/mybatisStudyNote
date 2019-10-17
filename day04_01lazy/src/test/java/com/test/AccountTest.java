package com.test;

import com.dao.IAccountDao;
import com.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private InputStream in;
    private SqlSession session;
    private IAccountDao accountDao;
    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建一个SqlSeesionFactory
        /*将文件解析交给mybatis*/
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产Seesion对象
        session = factory.openSession(true);
        //4.使用SqlSession创建Dao接口的代理对象
        accountDao = session.getMapper(IAccountDao.class);
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
        List<Account> accounts = accountDao.findAll();
        for(Account a : accounts){
            System.out.println("单个account数据");
            System.out.println(a);
            System.out.println(a.getUser());
        }
    }
}
