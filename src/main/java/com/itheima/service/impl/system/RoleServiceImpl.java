package com.itheima.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.itheima.domain.system.Role;
import com.itheima.mapper.system.RoleMapper;
import com.itheima.service.system.RoleService;
import com.itheima.utils.MapperFactory;
import com.mysql.jdbc.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> findAll(String pageNum, String pageSize) {
        /**
         * 查询所有的方法
         *
         * @param pageNum
         * @param pageSize
         * @return
         */
        SqlSession session = null;
        List<Role> list = null;

        try {

            //先获取session对象
            session = MapperFactory.getSqlSession();
            //获取mapper对象,获取到持久层接口的.class文件
            RoleMapper mapper = session.getMapper(RoleMapper.class);

            //设置默认的页码为1,如果传过来的是空,那么就是1
            int pageN = 1;
            //设置每页显示的条目数默认为10,如果传过来的是空,那么就是10
            int pageS = 10;
            //对获取的当前页码和每页显示条目数进来类型处理
            //调用StringUtils工具类,判断参数是否不为空.
            // * 1.如果不为空返回true。
            // * 2.如果为空返回false。
            if (!StringUtils.isNullOrEmpty(pageNum)) {
                //如果为空则把pageNum赋值给pageN
                //传过来的是字符串类型,所以需要在业务层进行处理,转换为int类型后处理页面
                pageN = Integer.parseInt(pageNum);
            }

            if (!StringUtils.isNullOrEmpty(pageSize)) {

                //如果为空则把pageNum赋值给pageN
                //传过来的是字符串类型,所以需要在业务层进行处理,转换为int类型后处理页面
                pageS = Integer.parseInt(pageSize);
            }

            //设置分页参数：当前页码，每页显示的条目数
            //调用import com.github.pagehelper.PageHelper;
            PageHelper.startPage(pageN, pageS);

            //然后调用持久层的查询所有方法,返回Company实体类类型的集合对象
            list = mapper.findAll();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            //如果报错,不放在finally里就不会关流了,就会造成数据的浪费
            session.close();
        }
        //将获得的集合参数返回
        return list;
    }

    @Override
    public List<Role> list() {
        SqlSession session = null;
        List<Role> list = null;
        try {
            //先获取session对象
            session = MapperFactory.getSqlSession();

            //获取mapper对象,获取到持久层接口的.class文件
            RoleMapper mapper = session.getMapper(RoleMapper.class);

            //调用持久层接口的findAll方法
            list = mapper.findAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            //如果报错,不放在finally里就不会关流了,就会造成数据的浪费
            session.close();
        }
        return list;
    }


    @Override
    public void save(Role role) {
        //先获取session对象
        SqlSession session = MapperFactory.getSqlSession();

        //获取mapper对象,获取到持久层接口的.class文件
        RoleMapper mapper = session.getMapper(RoleMapper.class);


        //生成随机ID
        role.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        //调用持久层接口的save方法
        mapper.save(role);

        //修改了数据,所以需要事务提交
        session.commit();
        //关闭资源
        session.close();
    }

    @Override
    public boolean delete(String ids) {
        SqlSession session = null;
        //首先定义一个布尔类型的flag,默认是false
        //表示如果flag日后为false就是删除失败的意思
        boolean flag = false;
        try {
            //根据工具类获取到session对象
            session = MapperFactory.getSqlSession();
            //然后获取到动态代理开发对象 得到持久层接口的.class文件
            RoleMapper mapper = session.getMapper(RoleMapper.class);
            //因为id是String类型的值,而我们也需要String类型的id
            // 所以,不用转换为int类型,直接根据逗号切割,切除掉多余的逗号,
            //然后将鼠标选中的一一存入数组即可,["1","2","3"]
            //可以用push方法,自动压栈,自动去掉多余逗号实现
            String[] id = ids.split(",");

            //调用持久层的删除方法,传入处理好的id
            mapper.delete(id);

            //然后传入成功后,把布尔类型的flag修改为true,
            // 用来在servlet进行判断,是否删除成功
            flag = true;

            //因为修改了数据库,所以需要事务提交
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            session.close();
        }
        //然后返回目前flag的布尔类型就可以了
        return flag;
    }

    //根据id查询
    @Override
    public Role findById(String id) {
        SqlSession session = null;
        RoleMapper mapper = null;
        try {
            //获取SqlSession
            session = MapperFactory.getSqlSession();
            //获取mapper对象,获取到持久层接口的.class文件
            mapper = session.getMapper(RoleMapper.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //3.调用Mapper层操作
        return mapper.findById(id);
    }

    @Override
    public void update(Role role) {
        SqlSession session = null;
        try {
            //获取SqlSession
            session = MapperFactory.getSqlSession();
            //获取mapper对象,获取到持久层接口的.class文件
            RoleMapper mapper = session.getMapper(RoleMapper.class);
            //调用持久层操作
            mapper.update(role);
            //提交事务
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //修改角色资源权限关系
    @Override
    public void updateRolePermission(String roleId, String permissionIds) {
        SqlSession session = null;
        try {
            //1.获取SqlSession
            session = MapperFactory.getSqlSession();
            //2.获取RoleMapper
            RoleMapper RoleMapper = MapperFactory.getMapper(session, RoleMapper.class);
            //3.调用持久层操作
            //修改role_permission
            //3.1现有的关系全部取消掉
            RoleMapper.deleteRolePermission(roleId);
            //3.2建立新的关系（多个）
            String[] permissionArray = permissionIds.split(",");
            //循环遍历
            for (String permissionId : permissionArray) {
                //调用持久层的方法
                RoleMapper.saveRolePermission(roleId, permissionId);
            }
            //提交事务
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //根据用户id查询其所对应的所有角色的方法
    @Override
    public List<Role> findAllRoleByUsersId(String usersId) {
        SqlSession session = null;
        RoleMapper roleMapper = null;
        try {
            //获取SqlSession
            session = MapperFactory.getSqlSession();
            //获取持久层
            roleMapper = MapperFactory.getMapper(session, RoleMapper.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用持久层操作
        return roleMapper.findAllRoleByUsersId(usersId);

    }

    @Override
    public Role findRole(String roleId) {
        //获取SqlSession
        SqlSession session = MapperFactory.getSqlSession();
        //获取持久层
        RoleMapper roleMapper = MapperFactory.getMapper(session, RoleMapper.class);

        Role role = roleMapper.findById(roleId);

        session.close();

        return role;
    }
}
