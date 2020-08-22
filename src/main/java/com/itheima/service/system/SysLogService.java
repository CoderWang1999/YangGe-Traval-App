package com.itheima.service.system;

import com.itheima.domain.system.SysLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface SysLogService {

    //添加日志
    void save(SysLog sysLog);

    List<SysLog> findAll(String pageNum, String pageSize);

    boolean deleteById(String ids);
         //文件上传
    ByteArrayOutputStream getReport() throws IOException;

    //模糊查询
    List<SysLog> findLikeTime(String find, String pageNum, String pageSize);




}
