package com.itheima.mapper.system;

import com.itheima.domain.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
持久层接口
 */
public interface RoleMapper {
    //查询所有的方法
    List<Role> findAll();

    //保存方法
    void save(Role role);

    //批量删除方法
    void delete(String[] id);

    //根据id查询方法
    Role findById(String id);

    //修改方法
    void update(Role role);

    //根据角色删除资源权限关系
    void deleteRolePermission(String roleId);

    // 保存角色 资源权限关系
    void saveRolePermission(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    //根据用户id查询其所对应的所有角色
    List<Role> findAllRoleByUsersId(String usersId);

    //根据用户ID查询所有的角色信息 flag标记了一个状态信息,表示如果跟我的角色有关就是被选中
    List<Role> findByIdForRole(String uid);
}
