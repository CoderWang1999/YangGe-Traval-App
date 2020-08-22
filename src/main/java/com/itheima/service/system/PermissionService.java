package com.itheima.service.system;

import com.itheima.domain.system.Permission;

import java.util.List;

/**
 * @author: LHL
 * @date: 2020-08-20 23:45
 */
public interface PermissionService {
    List<Permission> findAll(String pageNum, String pageSize);

    void save(Permission permission);

    boolean deleteByIds(String ids) throws Exception;

    boolean deleteById(String id) throws Exception;

    List<Permission> query(String name, String pageNum, String pageSize);

    List<Permission> findAllPermissionByRoleId(String roleId);
}
