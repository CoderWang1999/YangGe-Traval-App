package com.itheima.service.basedata;

import com.itheima.domain.basedata.Orders;

import java.util.List;

public interface OrdersService {
    //查询所有
    List<Orders> findAll(String pageNum, String pageSize);

    //批量删除
    boolean deleteByids(String ids);

    //根据id查询
    Orders findById(String id);

    //根据产品名称模糊查询
    List<Orders> findLikePname(String find, String pageNum, String pageSize);
}
