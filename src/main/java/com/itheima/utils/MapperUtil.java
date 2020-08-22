package com.itheima.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/*
* 持久层操作的一个封装类
* */
public class MapperUtil {
    private MapperUtil(){}
    private static SqlSessionFactory factory =null;
    static {
        try {
            //1、读取核心配置文件
            InputStream is = Resources.getResourceAsStream("sqlConfig.xml");
            //2、构建工厂对象
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获取session
    public static SqlSession getSession(){
        return factory.openSession();
    }

    //释放资源
    public static void close(SqlSession session){
        //6、事务提交
        session.commit();
        //7、释放资源
        session.close();
    }
}
