package com.itheima.mapper.system;

import com.itheima.domain.system.SysLog;

import java.util.List;

public interface SysLogMapper {

    //新增
    void save(SysLog sysLog);

    List<SysLog> findAll();


    //删除
    void deleteById(String[] id);
//模糊查询


    List<SysLog> findLikeTime(String str);
}
