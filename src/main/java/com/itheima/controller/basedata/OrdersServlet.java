package com.itheima.controller.basedata;

import com.github.pagehelper.PageInfo;
import com.itheima.controller.BaseServlet;
import com.itheima.domain.basedata.Orders;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/basedata/ordres/*")
public class OrdersServlet extends BaseServlet {
    //遍历所有
    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面数据得到当前页和每页显示条目数 以及模糊查询数据
        String find = req.getParameter("find");
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");
        //解决中文乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        if (StringUtils.isNullOrEmpty(find)){
            //调用service层的findAll方法
            List<Orders> list = ordersService.findAll(pageNum, pageSize);
            //将数据交由pageinfo处理
            PageInfo<Orders> pageInfo = new PageInfo<Orders>(list);
            //将查询出的数据存入请求域
            req.setAttribute("page", pageInfo);
        }else {
            List<Orders> list = ordersService.findLikePname(find, pageNum, pageSize);
            PageInfo<Orders> pageInfo = new PageInfo<>(list);
            req.setAttribute("find", find);
            req.setAttribute("page", pageInfo);
        }
        //请求转发
        req.getRequestDispatcher("/WEB-INF/pages/basedata/order/orders-list.jsp").forward(req, resp);
    }

    //批量删除
    private void deleteByids(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面数据
        String ids = req.getParameter("ids");
        //调用service层的deleteByids方法
        boolean flag = ordersService.deleteByids(ids);
        if (flag) {
            //删除成功之后查询所有
            findAll(req, resp);
        } else {
            //删除失败提示用户删除失败
            resp.getWriter().write("删除失败，两秒后自动跳转到显示所有订单信息页面");
            String path = req.getContextPath();
            resp.setHeader("refresh", "2;url="+req.getContextPath()+"/basedata/ordres/findAll");
        }
    }

    //详情页面回显
    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面数据得到订单id
        String id = req.getParameter("id");
        //调用service层根据订单id查找的方法
        Orders orders=ordersService.findById(id);
        //将查询结果放入请求域对象中
        req.setAttribute("orders",orders);
        //请求转发到show.jsp
        req.getRequestDispatcher("/WEB-INF/pages/basedata/order/orders-show.jsp").forward(req,resp);
    }
}
