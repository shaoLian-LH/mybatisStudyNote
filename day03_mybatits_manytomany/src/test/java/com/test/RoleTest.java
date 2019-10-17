package com.test;

import com.dao.IRoleDao;
import com.domain.Role;
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

public class RoleTest {
    private InputStream in;
    private SqlSession session;
    private IRoleDao roleDao;
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
        roleDao = session.getMapper(IRoleDao.class);
    }
    @After //用于在测试方法执行之后执行
    public void closeAll() throws IOException{
        //6.释放资源
        session.close();
        in.close();
    }
    @Test
    public void testFindAll() throws IOException {
        List<Role> roles = roleDao.findAll();
        for(Role r : roles){
            System.out.println(r);
        }
    }
}
