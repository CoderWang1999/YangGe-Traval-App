package com.itheima.mapper.system;

import com.itheima.domain.system.Permission;
import com.itheima.domain.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User findUserByUsernameandPwd(@Param("username") String username, @Param("password") String password);

    User findUserByEmailandPwd(@Param("email") String email, @Param("password") String password1);

    List<Permission> findPermissionByUid(String id);

    List<User> findAll();

    void save(User user);

    void deleteByIds(String[] ids);


    void deleteRoleById(String uid);

    void saveRole(@Param("uid") String uid, @Param("rId") String rId);

    User findUserById(String uid);


    List<User> findAllLikeByName(String username);

    void updateUserStatus(User user);
}
