package com.dao.impl;

import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.session.*;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    private SqlSessionFactory factory;
    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }
    public List<User> findAll(){
        //1.使用工厂船舰SqlSession对象
        SqlSession session = factory.openSession();
        //对照配置文件的namespace唯一指定了dao
        List<User> users = session.selectList("com.dao.IUserDao");
        session.close();
        return users;
    }
}
