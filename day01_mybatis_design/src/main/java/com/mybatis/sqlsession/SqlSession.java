package com.mybatis.sqlsession;

/**
 * 和数据库江湖的核心类
 * 可以创建dao接口的代理对象
 */
public interface SqlSession {
    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);
    void close();
}
