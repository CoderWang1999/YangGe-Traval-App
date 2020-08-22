package com.itheima.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.controller.BaseServlet;
import com.itheima.domain.system.Permission;
import com.itheima.domain.system.Role;
import com.itheima.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet/role/*")
public class RoleServlet extends BaseServlet {

    private void toMain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/home/main.jsp").forward(req, resp);
    }

 /*   private void toRoleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/role-list.jsp").forward(req,resp);
    }
*/


    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取从前端传来的参数
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");

        //调用业务层接口的查询所有的方法
        List<Role> list = roleService.findAll(pageNum, pageSize);
        //创建分页对象 把返回的list集合传入
        //数据交由分页插件处理获取分页信息
        PageInfo<Role> pageInfo = new PageInfo<Role>(list);
        //将pageInfo存入作用域中
        req.setAttribute("page", pageInfo);
        //请求转发到list.jsp"页面
        req.getRequestDispatcher("/WEB-INF/pages/system/role/role-list.jsp").forward(req, resp);
    }


    //查询所有数据的方法 ,不带分页参数,因为为了给添加的方法使用
    private void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Role> roleList = roleService.list();
        //设置作用域
        req.setAttribute("roleList", roleList);
        //请求转发到
        req.getRequestDispatcher("/WEB-INF/pages/system/role/role-add.jsp").forward(req, resp);
    }


    //添加方法
    private void save(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //将数据获取到，封装成一个对象
        Role role = BeanUtil.fillBean(req, Role.class);


        //调用业务层的save方法 在BaseServlet中封装了业务层接口对象
        roleService.save(role);

        //添加完成后,然后再调用findAll,再次查看添加的结果
        // findAll(req, resp);
        resp.sendRedirect(req.getContextPath() + "/servlet/role/findAll");

    }


    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //首先获取前端穿来的id 在list.jsp中创建了deleteById方法,
        // 得到鼠标选中的id,然后进行传递到后端
        String ids = req.getParameter("ids");
        //调用业务层的deleteByIds方法传入得到鼠标选中的id
        boolean flag = roleService.delete(ids);

        //在业务层实现类中定义了flag 是一个布尔类型的值,
        // if(flag)就是 if(true)的意思
        if (flag) {
            //删除完毕后,再次回到findAll方法,展示删除后的数据
            resp.sendRedirect(req.getContextPath() + "/servlet/role/findAll");
        } else {
            //如果是false,自定义了一个错误页面,跳转到错误页面即可
            try {
                req.getRequestDispatcher("/WEB-INF/pages/common/error.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //根据id进行查询
    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询要修改的数据findById
        String id = request.getParameter("id");

        Role role = roleService.findById(id);


        //将数据加载到指定区域，供页面获取
        request.setAttribute("role", role);

        //跳转页面
        request.getRequestDispatcher("/pages/servlet/role/????").forward(request, response);
    }

    //修改方法
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取到，封装成一个对象
        Role role = BeanUtil.fillBean(req, Role.class);
        //调用业务层接口
        roleService.update(role);

        /*Map<String, String[]> parameterMap = req.getParameterMap();
        parameterMap.get("id");
        parameterMap.get("name");*/

        //跳转回到页面
        resp.sendRedirect(req.getContextPath() + "/system/role/findAll");
    }


    //根据当前角色找到对应的资源权限关系
    private void author(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //获取要授权的角色id
        String roleId = request.getParameter("id");
        //使用id查询对应的数据（角色id对应的资源权限关系信息）
        Role role = roleService.findById(roleId);
        request.setAttribute("role", role);
        //根据当前的角色id获取所有的资源权限关系，并加载关系数据
        //  List<Map> map = permissionService.findAuthorDataByRoleId(roleId);

        //map转成json数据
        //    ObjectMapper om = new ObjectMapper();
        //    String json = om.writeValueAsString(map);
        //     request.setAttribute("rolePermissionJson",json);
        /*  // TODO 数据未查询*/

        //跳转到树页面中
        request.getRequestDispatcher("/WEB-INF/pages/system/role/???.jsp").forward(request, response);
    }


    /**
     * 通过角色id修改对应权限资源管理
     *
     * @param req
     * @param resp
     * @throws IllegalStateException
     * @throws IOException
     * @throws ServletException
     */
    /*protected void toUpdatePermission(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException {
        //获取前段对象
        String roleId = req.getParameter("roleId");

        //进入业务层
        //  List<Permission> permissionList =  permissionService.toUpdateRole(roleId);


        //   req.setAttribute("permissionList", permissionList);
        req.setAttribute("roleId", roleId);

        req.getRequestDispatcher("/WEB-INF/pages/system/user/user-role-add.jsp").forward(req, resp);
    }*/




    /**
     * 通过角色id对象,以及查询对应的所有权限
     *类似findAll
     * @param req
     * @param resp
     * @throws IllegalStateException
     * @throws IOException
     * @throws ServletException
     */
    protected void findRoleAndPermission(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException {
        //获取前段对象
        String roleId = req.getParameter("roleId");
        //获取Role对象
        Role role = roleService.findRole(roleId);

        List<Permission> permissionList = permissionService.findAllPermissionByRoleId(roleId);
        //System.out.println("permissionList = " + permissionList);
        req.setAttribute("permissionList", permissionList);
        req.setAttribute("role", role);


        req.getRequestDispatcher("/WEB-INF/pages/system/role/role-permission-add.jsp").forward(req, resp);
    }


    //修改角色的资源权限关系
    private void updateRolePermission(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //得到角色的id
        String roleId = request.getParameter("roleId");
        //得到资源权限关系的id
        String permissionIds = request.getParameter("ids");
        //调用业务层方法
        roleService.updateRolePermission(roleId, permissionIds);


        request.setAttribute("roleId", roleId);
        //调用方法
        findRoleAndPermission(request, response);

        // response.sendRedirect(request.getContextPath()+"/system/role/findAll");
    }






}