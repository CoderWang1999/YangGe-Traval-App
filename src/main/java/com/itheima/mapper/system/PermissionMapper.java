package com.itheima.mapper.system;

import com.itheima.domain.system.Permission;

import java.util.List;

/**
 * @author: LHL
 * @date: 2020-08-20 23:45
 */
public interface PermissionMapper {
    List<Permission> findAll();

    void save(Permission permission);

    void deleteByIds(String[] id) throws Exception;

    void deleteById(String id) throws Exception;

    List<Permission> query(String name);

    List<Permission> findAllPermissionByRoleId(String roleId);
}
