package com.itheima.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.itheima.domain.system.Permission;
import com.itheima.mapper.system.PermissionMapper;
import com.itheima.service.system.PermissionService;
import com.itheima.utils.MapperUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<Permission> findAll(String pageNum,String pageSize) {
        SqlSession session = MapperUtil.getSession();
        PermissionMapper mapper = session.getMapper(PermissionMapper.class);

        int pageN=1;
        int pageS=5;

        if (StringUtils.isNotBlank(pageNum)){
            pageN=Integer.parseInt(pageNum);
        }
        if (StringUtils.isNotBlank(pageSize)){
            pageS=Integer.parseInt(pageSize);
        }

        PageHelper.startPage(pageN,pageS);

        List<Permission> list=mapper.findAll();

        MapperUtil.close(session);

        return list;
    }

    @Override
    public void save(Permission permission) {
        SqlSession session = MapperUtil.getSession();
        PermissionMapper mapper = session.getMapper(PermissionMapper.class);
        String id = UUID.randomUUID().toString().replace("-", "");
        permission.setId(id);
        mapper.save(permission);
        MapperUtil.close(session);
    }

    @Override
    public boolean deleteByIds(String ids) throws Exception{
        boolean flag =false;
        SqlSession session =null;
        try {
            session = MapperUtil.getSession();
            PermissionMapper mapper = session.getMapper(PermissionMapper.class);
            String[] id = ids.split(",");
            mapper.deleteByIds(id);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MapperUtil.close(session);
        }
        return  flag;
    }

    @Override
    public boolean deleteById(String id) throws Exception{
        boolean flag =false;
        SqlSession session =null;
        try {
            session = MapperUtil.getSession();
            PermissionMapper mapper = session.getMapper(PermissionMapper.class);
            mapper.deleteById(id);

            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MapperUtil.close(session);
        }
        return flag;
    }

    @Override
    public List<Permission> query(String name, String pageNum, String pageSize) {
        SqlSession session = MapperUtil.getSession();
        PermissionMapper mapper = session.getMapper(PermissionMapper.class);
        int pageN=1;
        int pageS=5;

        if (StringUtils.isNotBlank(pageNum)){
            pageN=Integer.parseInt(pageNum);
        }
        if (StringUtils.isNotBlank(pageSize)){
            pageS=Integer.parseInt(pageSize);
        }

        PageHelper.startPage(pageN,pageS);

        List<Permission> list=mapper.query(name);

        MapperUtil.close(session);

        return list;
    }

    @Override
    public List<Permission> findAllPermissionByRoleId(String roleId) {
        SqlSession session = MapperUtil.getSession();
        PermissionMapper mapper = session.getMapper(PermissionMapper.class);

        List<Permission> list=  mapper.findAllPermissionByRoleId(roleId);


        return list;
    }
}