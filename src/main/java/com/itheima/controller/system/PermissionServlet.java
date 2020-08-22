package com.itheima.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.controller.BaseServlet;
import com.itheima.domain.system.Permission;
import com.itheima.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/permission/*")
public class PermissionServlet extends BaseServlet {
    /**
     * 遍历所有
     * @param req
     * @param resp
     */
    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");

        List<Permission> list= permissionService.findAll(pageNum,pageSize);
        PageInfo<Permission> pageInfo=new PageInfo<Permission>(list);
        req.setAttribute("page",pageInfo);
        req.getRequestDispatcher("/WEB-INF/pages/system/permission/permission-list.jsp").forward(req,resp);
    }

    private void toSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/system/permission/permission-add.jsp").forward(req, resp);
    }

    /**
     * 添加数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Permission permission = BeanUtil.fillBean(req, Permission.class);
        permissionService.save(permission);
        findAll(req,resp);
    }

    /**
     * 批量删除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String ids = req.getParameter("ids");
        boolean flag = permissionService.deleteByIds(ids);
        if (flag){
            findAll(req,resp);
        }else {
            req.getRequestDispatcher("/WEB-INF/pages/system/permission/permission-list.jsp").forward(req,resp);
        }

    }

    /**
     * 单个删除
     * @param req
     * @param resp
     */
    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        boolean flag = permissionService.deleteById(id);
        if (flag){
            findAll(req,resp);
        }else {
            req.getRequestDispatcher("/WEN-INF/pages/common/failer.jsp").forward(req,resp);
        }
    }
    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");
        String name = req.getParameter("name");

        List<Permission> list= permissionService.query(name,pageNum,pageSize);
        PageInfo<Permission> pageInfo=new PageInfo<Permission>(list);

        req.setAttribute("name",name);
        req.setAttribute("page",pageInfo);
        req.getRequestDispatcher("/WEB-INF/pages/system/permission/permission-list.jsp").forward(req,resp);
    }
}
