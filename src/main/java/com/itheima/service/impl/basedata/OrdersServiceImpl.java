package com.itheima.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.itheima.domain.basedata.Orders;
import com.itheima.domain.basedata.Traveller;
import com.itheima.mapper.basedata.OrdersMapper;
import com.itheima.service.basedata.OrdersService;
import com.itheima.utils.MapperFactory;
import com.itheima.utils.TransactionUtil;
import com.mysql.jdbc.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {

    //查询所有订单信息
    @Override
    public List<Orders> findAll(String pageNum, String pageSize) {
        SqlSession session =null;
        try {
            //获取Sqlsession对象
            session = MapperFactory.getSqlSession();
            //获取代理对象
            OrdersMapper mapper = MapperFactory.getMapper(session, OrdersMapper.class);
            //设置默认首页
            int currentPage=1;
            //设置默认每页显示条数
            int ps=5;
            //如果获取到的数据不为空字符串并且有值则赋值
            if (!StringUtils.isNullOrEmpty(pageNum)){
                currentPage=Integer.parseInt(pageNum);
            }
            if (!StringUtils.isNullOrEmpty(pageSize)){
                ps=Integer.parseInt(pageSize);
            }
            //设置分页参数
            PageHelper.startPage(currentPage,ps);
            //执行Sql语句
            List<Orders> list = mapper.findAll();
            //设置时间格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Orders orders : list) {
                orders.setOrderTimeStr(sdf.format(orders.getOrderTime()));
                //将订单状态转换成用户看的懂得信息
                if (orders.getOrderStatus()==1){
                    orders.setOrderStatusStr("已下单");
                }else if (orders.getOrderStatus()==0){
                    orders.setOrderStatusStr("没下单");
                }
            }
            //返回结果
            return list;
        }catch (Exception e){
            throw new RuntimeException("查询失败！");
        }finally {
            TransactionUtil.close(session);
        }
    }

    //批量删除
    @Override
    public boolean deleteByids(String ids) {
        SqlSession session=null;
        boolean flag=false;//默认删除失败
        try {
            //获取Sqlsession对象
            session = MapperFactory.getSqlSession();
            //获取代理对象
            OrdersMapper mapper = MapperFactory.getMapper(session, OrdersMapper.class);
            //处理lds
            String[] Ids = ids.split(",");
            //执行sql语句
            //先批量删除order_traveller表中对应的订单id
            mapper.deleteByorderIds(Ids);
            //再批量删除订单详细信息
            mapper.deleteByids(Ids);
            //执行到这里说明删除成功
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //提交事务释放资源
            TransactionUtil.commit(session);
            TransactionUtil.close(session);
        }
        return flag;
    }

    //根据订单id查询
    @Override
    public Orders findById(String id) {
        //获取Sqlsession对象
        SqlSession session = MapperFactory.getSqlSession();
        //获取代理对象
        OrdersMapper mapper = MapperFactory.getMapper(session, OrdersMapper.class);
        //执行Sql语句
        Orders orders = mapper.findById(id);
        //提交事务释放资源
        TransactionUtil.close(session);
        //设置时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        orders.setOrderTimeStr(sdf.format(orders.getOrderTime()));
        //将订单状态转换成用户看的懂得信息
        //订单状态
        if (orders.getOrderStatus()==1){
            orders.setOrderStatusStr("已下单");
        }else if (orders.getOrderStatus()==0){
            orders.setOrderStatusStr("没下单");
        }
        // 人群   证件类型
        for (Traveller traveller : orders.getTravellers()) {
            if (traveller.getCredentialsType()==0){
                traveller.setCredentialsTypeStr("大陆居民身份证");
            }else {
                System.out.println("非大陆居民身份证");
            }
        }
        //支付方式
        if (orders.getPayType()==1){
            orders.setPayTypeStr("现金支付");
        }else {
            orders.setPayTypeStr("线上支付");
        }
        //返回结果
        return orders;
    }

    //根据产品名称模糊查询
    @Override
    public List<Orders> findLikePname(String find, String pageNum, String pageSize) {
        SqlSession session =null;
        try {
            //获取Sqlsession对象
            session = MapperFactory.getSqlSession();
            //获取代理对象
            OrdersMapper mapper = MapperFactory.getMapper(session, OrdersMapper.class);
            //设置默认首页
            int currentPage=1;
            //设置默认每页显示条数
            int ps=5;
            //如果获取到的数据不为空字符串并且有值则赋值
            if (!StringUtils.isNullOrEmpty(pageNum)){
                currentPage=Integer.parseInt(pageNum);
            }
            if (!StringUtils.isNullOrEmpty(pageSize)){
                ps=Integer.parseInt(pageSize);
            }
            //获取string对象进行字符串操作
            StringBuilder stringBuilder = new StringBuilder(find);
            //合并模糊查询所需要的字符
            String Pname = new StringBuilder("%").append(find).append("%").toString();
            //设置分页参数
            PageHelper.startPage(currentPage,ps);
            //执行Sql语句
            List<Orders> list = mapper.findLikePname(Pname);
            //设置时间格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Orders orders : list) {
                orders.setOrderTimeStr(sdf.format(orders.getOrderTime()));
                //将订单状态转换成用户看的懂得信息
                if (orders.getOrderStatus()==1){
                    orders.setOrderStatusStr("已下单");
                }else if (orders.getOrderStatus()==0){
                    orders.setOrderStatusStr("没下单");
                }
            }
            //返回结果
            return list;
        }catch (Exception e){
            throw new RuntimeException("查询失败！");
        }finally {
            TransactionUtil.close(session);
        }
    }
}
