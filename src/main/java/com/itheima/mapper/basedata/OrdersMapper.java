package com.itheima.mapper.basedata;

import com.itheima.domain.basedata.Orders;

import java.util.List;

public interface OrdersMapper {
    //查询所有
    List<Orders> findAll();

    //批量删除
    void deleteByids(String[] ids);

    //批量删除order_traveller表中对应的订单id
    void deleteByorderIds(String[] ids);

    //根据订单id查询
    Orders findById(String id);

    //根据产品名称模糊查询
    List<Orders> findLikePname(String pname);

}
