package com.itheima.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 用于生成dao接口代理实现类的工厂
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class MapperFactory {

    private static SqlSessionFactory factory;
    private static ThreadLocal<SqlSession> tl = new ThreadLocal<SqlSession>();

    static {
        InputStream in = null;
        try {
            //1.读取mybatis主配置文件
            in = Resources.getResourceAsStream("sqlConfig.xml");
            //2.创建构建者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3.使用构建者创建SqlSessionFactory工厂
            factory = builder.build(in);
        }catch (Exception e){
            //打印异常信息到控制台
            e.printStackTrace();
            //抛出错误提示程序终止执行
            throw new ExceptionInInitializerError("初始化SqlSessionFactory失败");
        }finally {
            //释放流对象
            if(in != null){
                try{
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取SqlSession对象
     * @return
     * 保留此方法是为了后面对业务层方法增强，利用AOP添加事务
     */
    public static SqlSession getSqlSession(){
         return factory.openSession(false);
    }

    /**
     * 获取Dao接口的代理实现类
     * @param daoClass dao接口字节码
     * @return
     */
    public static <T> T getMapper(SqlSession sqlSession,Class<T> daoClass){
        //1.判断传入的SqlSession是否为null
        if(sqlSession == null){
            return null;
        }
        //2.不为null，创建代理实现类
        return sqlSession.getMapper(daoClass);
    }
}
