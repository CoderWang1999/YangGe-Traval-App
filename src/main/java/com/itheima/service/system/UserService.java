package com.itheima.service.system;

import com.itheima.domain.system.Permission;
import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;

import java.util.List;

public interface UserService {
    User findUserByUsernameandPwd(String username, String password);

    User findUserByEmailandPwd(String email, String password);

    List<Permission> findPermissionByUid(String id);

    List<User> findAll(String pageNum, String pageSize);

    void save(User user);

    void delete(String id);

    List<Role> toUpdateUser(String uid);

    void addRoleToUser(String uid, String ids);

    User findUser(String uid);

    List<User> findAllLikeByName(String pageNum, String pageSize, String find);


    void updateUserStatus(User user);
}
