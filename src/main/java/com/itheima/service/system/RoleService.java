package com.itheima.service.system;

import com.itheima.domain.system.Role;

import java.util.List;

/*
业务层接口
 */
public interface RoleService {

    List<Role> findAll(String pageNum, String pageSize);

    List<Role> list();

    void save(Role role);

    boolean delete(String ids);

    Role findById(String id);

    void update(Role role);

    /**
     * 建立角色与资源权限关系之间的关联
     * @param roleId 角色id
     * @param permissionIds 资源权限关系id（多个）
     */
    void updateRolePermission(String roleId, String permissionIds);


        //根据用户id查询其拥有的所有角色的方法
    List<Role> findAllRoleByUsersId(String usersId);


    Role findRole(String roleId);
}
