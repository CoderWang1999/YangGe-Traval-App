package com.itheima.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.itheima.domain.system.Permission;
import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;
import com.itheima.mapper.system.RoleMapper;
import com.itheima.mapper.system.UserMapper;
import com.itheima.service.system.UserService;
import com.itheima.utils.MD5Util;
import com.itheima.utils.MapperFactory;
import com.itheima.utils.TransactionUtil;
import com.itheima.utils.UidUtil;
import com.mysql.jdbc.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {
    /**
     * 分页查询所有
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<User> findAll(String pageNum, String pageSize) {

        SqlSession session = MapperFactory.getSqlSession();
        UserMapper mapper = MapperFactory.getMapper(session, UserMapper.class);
        int pageN = 1;
        int pageS = 10;
        if (!StringUtils.isNullOrEmpty(pageNum)) {
            pageN = Integer.parseInt(pageNum);
        }
        if (!StringUtils.isNullOrEmpty(pageSize)) {
            pageS = Integer.parseInt(pageSize);
        }
        PageHelper.startPage(pageN, pageS);

        List<User> list = mapper.findAll();

        return list;
    }

    /**
     * @param user
     */
    public void save(User user) {
        SqlSession session = MapperFactory.getSqlSession();
        UserMapper mapper = MapperFactory.getMapper(session, UserMapper.class);
        //设置id
        user.setId(UidUtil.getUid());
        //对密码进行加密
        if (!StringUtils.isEmptyOrWhitespaceOnly(user.getPassword())) {
            String pwd = MD5Util.md5(user.getPassword());
            user.setPassword(pwd);
        }
        //数据库保存
        mapper.save(user);
        //提交事务
        TransactionUtil.commit(session);
        TransactionUtil.close(session);
    }

    /**
     * 通过id删除
     *
     * @param id id为一个数组的字符串
     */
    public void delete(String id) {
        //获取id的数组
        String[] ids = id.split(",");

        SqlSession session = MapperFactory.getSqlSession();
        UserMapper mapper = MapperFactory.getMapper(session, UserMapper.class);

        //由于id关联了role所以删除id之前需要删除关联表

        //通过id删除
        mapper.deleteByIds(ids);
        //提交事务
        TransactionUtil.commit(session);
        TransactionUtil.close(session);

    }

    /**
     * 通过user的id查询所有与user有管的角色进行修改
     *
     * @param uid
     */
    public List<Role> toUpdateUser(String uid) {
        SqlSession session = MapperFactory.getSqlSession();
        RoleMapper mapper = MapperFactory.getMapper(session, RoleMapper.class);


        List<Role> list = mapper.findByIdForRole(uid);

        //提交事务
        TransactionUtil.commit(session);
        TransactionUtil.close(session);

        return list;
    }

    /**
     * 通过uid删除所有的关联,再通过ids保存所有的关联
     *
     * @param uid
     * @param ids
     */
    public void addRoleToUser(String uid, String ids) {

        SqlSession session = MapperFactory.getSqlSession();
        UserMapper mapper = MapperFactory.getMapper(session, UserMapper.class);

        //由于id关联了role所以删除id之前需要删除关联表
        //获取id的数组
        mapper.deleteRoleById(uid);
        if (!StringUtils.isNullOrEmpty(ids)) {
            String[] id = ids.split(",");

            for (String rId : id) {

                mapper.saveRole(uid, rId);
            }

        }

        //提交事务
        TransactionUtil.commit(session);
        TransactionUtil.close(session);
    }

    /**
     * 通过id查询用户
     * @param uid
     * @return
     */
    public User findUser(String uid) {
        SqlSession session = MapperFactory.getSqlSession();
        UserMapper mapper = MapperFactory.getMapper(session, UserMapper.class);
        User user = mapper.findUserById(uid);
        return user;
    }


    /**
     * 分页查询所有
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<User> findAllLikeByName(String pageNum, String pageSize, String find) {

        SqlSession session = MapperFactory.getSqlSession();
        UserMapper mapper = MapperFactory.getMapper(session, UserMapper.class);
        int pageN = 1;
        int pageS = 5;
        if (!StringUtils.isNullOrEmpty(pageNum)) {
            pageN = Integer.parseInt(pageNum);
        }
        if (!StringUtils.isNullOrEmpty(pageSize)) {
            pageS = Integer.parseInt(pageSize);
        }
        PageHelper.startPage(pageN, pageS);

        String username = new StringBuilder("%").append(find).append("%").toString();
        List<User> list = mapper.findAllLikeByName(username);

        return list;
    }

    /**
     * 通过id修改用户的状态
     * @param user
     */
    public void updateUserStatus(User user) {
        SqlSession session = MapperFactory.getSqlSession();
        UserMapper mapper = MapperFactory.getMapper(session, UserMapper.class);

        mapper.updateUserStatus(user);



        //提交事务
        TransactionUtil.commit(session);
        TransactionUtil.close(session);
    }

    @Override
    public User findUserByUsernameandPwd(String username, String password) {
        SqlSession sqlSession = null;
        //为了保证遇到异常sqlSession对象也关闭 讲代码进行异常处理
        User user = null;

        try {
            sqlSession = MapperFactory.getSqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            String password1 = MD5Util.md5(password);

            user = mapper.findUserByUsernameandPwd(username,password1);

        } catch (Exception e) {
//            throw new RuntimeException("查询失败");
            e.printStackTrace();
        } finally {
            TransactionUtil.close(sqlSession);
        }
        return user;
    }

    @Override
    public User findUserByEmailandPwd(String email, String password) {
        SqlSession sqlSession = null;
        //为了保证遇到异常sqlSession对象也关闭 讲代码进行异常处理
        User user = null;

        try {
            sqlSession = MapperFactory.getSqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            String password1 = MD5Util.md5(password);

            user = mapper.findUserByEmailandPwd(email,password1);

        } catch (Exception e) {
//            throw new RuntimeException("查询失败");
            e.printStackTrace();
        } finally {
            TransactionUtil.close(sqlSession);
        }
        return user;
    }

    @Override
    public List<Permission> findPermissionByUid(String id) {
        SqlSession sqlSession = null;
        //为了保证遇到异常sqlSession对象也关闭 讲代码进行异常处理
        List<Permission> permissionList=null;

        try {
            sqlSession = MapperFactory.getSqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            permissionList = mapper.findPermissionByUid(id);

        } catch (Exception e) {
//            throw new RuntimeException("查询失败");
            e.printStackTrace();
        } finally {
            TransactionUtil.close(sqlSession);
        }
        return permissionList;
    }
}
