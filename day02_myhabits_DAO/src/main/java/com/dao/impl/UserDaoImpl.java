package com.dao.impl;

import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory = null;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }
    public List<User> findAll() {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法，实现查询列表
        //参数是能获取的配置信息的key
        //IUserDAO.xml中mapper的namespace.方法名
        List<User> users = session.selectList("com.dao.IUserDao.findAll");
        //释放资源
        session.close();
        return users;
    }

    public void saveUser(User user) {
        SqlSession session = factory.openSession();
        session.insert("com.dao.IUserDao.saveUser",user);
        session.commit();
        session.close();
    }

    public void updateUser(User user) {
        SqlSession session = factory.openSession();
        session.update("com.dao.IUserDao.updateUser",user);
        session.commit();
        session.close();
    }

    public void deleteUser(Integer userId) {
        SqlSession session = factory.openSession();
        session.delete("com.dao.IUserDao.deleteUser",userId);
        session.commit();
        session.close();
    }

    public User findById(Integer userId) {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法，实现查询
        //参数是能获取的配置信息的key
        //IUserDAO.xml中mapper的namespace.方法名
        User user = session.selectOne("com.dao.IUserDao.findById",userId);
        //释放资源
        session.close();
        return user;
    }

    public List<User> findByName(String username) {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法，实现查询列表
        //参数是能获取的配置信息的key
        //IUserDAO.xml中mapper的namespace.方法名
        List<User> users = session.selectList("com.dao.IUserDao.findByName",username);
        //释放资源
        session.close();
        return users;

    }

    public int findTotal() {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法，实现查询
        //参数是能获取的配置信息的key
        //IUserDAO.xml中mapper的namespace.方法名
        Integer count = session.selectOne("com.dao.IUserDao.findTotal");
        //释放资源
        session.close();
        return count;
    }
}
