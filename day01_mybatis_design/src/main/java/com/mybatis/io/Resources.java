package com.mybatis.io;

import java.io.InputStream;

/**
 * 利用类加载器读取篇日志文件的类
 */
public class Resources {
    /**
     * 根据传入的参数，获得字节输入流
     * @param filePath
     * @return
     */
    public static InputStream getResourceAsStream(String filePath) {
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
