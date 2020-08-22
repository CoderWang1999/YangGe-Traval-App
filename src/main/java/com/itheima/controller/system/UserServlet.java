package com.itheima.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.controller.BaseServlet;
import com.itheima.domain.system.Permission;
import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;
import com.itheima.utils.BeanUtil;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/system/user/*")
public class UserServlet extends BaseServlet {
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String ck = req.getParameter("ck");


        //通过用户名登录
        User user1 = userService.findUserByUsernameandPwd(username, password);
        //通过邮箱登录
        User user2 = userService.findUserByEmailandPwd(username, password);

        if (user1 != null || user2 != null) {
            //登陆成功

            //该用户所有的权限
            List<Permission> permissionList = null;
            if (user1 != null) {
                //根据用户id查找角色
                permissionList = userService.findPermissionByUid(user1.getId());
            } else {
                permissionList = userService.findPermissionByUid(user2.getId());
            }

            req.getSession().setAttribute("permissionList", permissionList);
            req.getSession().setAttribute("username", username);

            //设置下次自动登录
            Cookie username1 = new Cookie("username", username);
            Cookie pwd = new Cookie("pwd", password);

            if ("1".equals(ck)) {
                username1.setMaxAge(3600 * 24);
                pwd.setMaxAge(3600 * 24);
                username1.setPath("/");
                pwd.setPath("/");
            } else {
                for (Cookie cookie : req.getCookies()) {
                    cookie = new Cookie(cookie.getName(), null);//删除前必须要new 一个valu为空；
                    cookie.setPath("/");//路径要相同
                    cookie.setMaxAge(0);//生命周期设置为0
                }
            }

            resp.addCookie(username1);
            resp.addCookie(pwd);

            req.getRequestDispatcher("/WEB-INF/pages/home/main.jsp").forward(req, resp);
        } else {
            //登录失败，跳转到登录页面
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    /**
     * 查找所有
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException {
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");
        String find = req.getParameter("find");
        List<User> list = null;
        if (StringUtils.isNullOrEmpty(find)) {
            list = userService.findAll(pageNum, pageSize);
        } else {
            list = userService.findAllLikeByName(pageNum, pageSize, find);
        }
        PageInfo<User> userPageInfo = new PageInfo<>(list);
        req.setAttribute("page", userPageInfo);
        req.setAttribute("find", find);

        req.getRequestDispatcher("/WEB-INF/pages/system/user/user-list.jsp").forward(req, resp);
    }

    /**
     * 中转查询
     *
     * @param req
     * @param resp
     * @throws IllegalStateException
     * @throws IOException
     * @throws ServletException
     */
    protected void toAdd(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException {

        req.getRequestDispatcher("/WEB-INF/pages/system/user/user-add.jsp").forward(req, resp);
    }

    /**
     * 添加用户
     *
     * @param req
     * @param resp
     * @throws IllegalStateException
     * @throws IOException
     * @throws ServletException
     */
    protected void save(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException {
        //获取前段对象
        User user = BeanUtil.fillBean(req, User.class);

        //进入业务层
        userService.save(user);

        findAll(req, resp);
    }

    /**
     * 批量删除
     *
     * @param req
     * @param resp
     * @throws IllegalStateException
     * @throws IOException
     * @throws ServletException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException {
        //获取前段对象
        String id = req.getParameter("ids");

        //进入业务层
        userService.delete(id);

        findAll(req, resp);
    }

    /**
     * 通过id修改用户角色
     *
     * @param req
     * @param resp
     * @throws IllegalStateException
     * @throws IOException
     * @throws ServletException
     */
    protected void toUpdateUser(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException {
        //获取前段对象
        String uid = req.getParameter("uid");

        //进入业务层
        List<Role> list = userService.toUpdateUser(uid);


        req.setAttribute("roleList", list);
        req.setAttribute("uid", uid);

        req.getRequestDispatcher("/WEB-INF/pages/system/user/user-role-add.jsp").forward(req, resp);

    }


    /**
     * 通过id修改用户角色
     *
     * @param req
     * @param resp
     * @throws IllegalStateException
     * @throws IOException
     * @throws ServletException
     */
    protected void addRoleToUser(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException {
        //获取前段对象
        String uid = req.getParameter("uid");
        String ids = req.getParameter("ids");


        //进入业务层
        userService.addRoleToUser(uid, ids);
        req.setAttribute("uid", uid);

        findAll(req,resp);
    }

    /**
     * 通过id查找用户和角色
     *
     * @param req
     * @param resp
     * @throws IllegalStateException
     * @throws IOException
     * @throws ServletException
     */
    protected void findUserAndRole(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException {
        //获取前段对象
        String uid = req.getParameter("uid");
        //获取user对象
        User user = userService.findUser(uid);
        List<Role> roleList = roleService.findAllRoleByUsersId(uid);
        //  System.out.println("roleList = " + roleList);

        req.setAttribute("roleList", roleList);
        req.setAttribute("user", user);

        //进入业务层
        req.getRequestDispatcher("/WEB-INF/pages/system/user/user-detail.jsp").forward(req, resp);


    }


    /**
     * 通过id修改用户的状态
     *
     * @param req
     * @param resp
     * @throws IllegalStateException
     * @throws IOException
     * @throws ServletException
     */
    protected void changeStatus(HttpServletRequest req, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException {
        //获取前段对象
        String id = req.getParameter("id");
        //进入业务层处理
        User user = userService.findUser(id);
        System.out.println("user = " + user);
        if (user.getStatus() == 1) {
            user.setStatus(0);
            userService.updateUserStatus(user);
        } else {
            user.setStatus(1);
            userService.updateUserStatus(user);
        }
        System.out.println("user = " + user);
        //进入业务层
        findAll(req, resp);
    }
}
