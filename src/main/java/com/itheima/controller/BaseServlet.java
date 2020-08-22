package com.itheima.controller;

import com.itheima.domain.system.Permission;
import com.itheima.domain.system.SysLog;
import com.itheima.service.basedata.OrdersService;
import com.itheima.service.basedata.ProductService;
import com.itheima.service.impl.basedata.OrdersServiceImpl;
import com.itheima.service.impl.basedata.ProductServiceImpl;
import com.itheima.service.impl.system.PermissionServiceImpl;
import com.itheima.service.impl.system.RoleServiceImpl;
import com.itheima.service.impl.system.SysLogServiceImpl;
import com.itheima.service.impl.system.UserServiceImpl;
import com.itheima.service.system.PermissionService;
import com.itheima.service.system.RoleService;
import com.itheima.service.system.SysLogService;
import com.itheima.service.system.UserService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BaseServlet extends HttpServlet {
    protected SysLogService sysLogService = null;
    protected UserService userService = null;
    protected PermissionService permissionService = null;
    protected RoleService roleService = null;
    protected OrdersService ordersService=null;
    protected ProductService productService = null;

    @Override

    public void init() throws ServletException {
        sysLogService = new SysLogServiceImpl();
        userService = new UserServiceImpl();
        permissionService = new PermissionServiceImpl();
        roleService = new RoleServiceImpl();
        ordersService=new OrdersServiceImpl();
        productService = new ProductServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            StringBuffer requestURL = req.getRequestURL();

            if (requestURL.toString().contains("login")) {
                //从请求的url中获取方法名
                String methodName = requestURL.substring(requestURL.lastIndexOf("/") + 1);
                //获取要执行的方法的method对象，另外参数如果是req.getClass()会失败！！！
                Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                //暴力反射执行私有方法
                method.setAccessible(true);
                //执行方法
                long time1 = System.currentTimeMillis();
                method.invoke(this, req, resp);
                long time2 = System.currentTimeMillis();
                //记录日志
                syslog(req, time1, time2, methodName);

                return;
            }




            List<Permission> permissionList = (List<Permission>) req.getSession().getAttribute("permissionList");

            if (permissionList == null || permissionList.size() == 0) {
                req.getRequestDispatcher("/WEB-INF/pages/common/error.jsp").forward(req, resp);
                return;
            }

            //测试数据
            System.out.println("111111111111111111111111111");

            for (Permission permission : permissionList) {
                //判断用户的权限在不在请求的url中
                if (requestURL.toString().contains(permission.getUrl())) {
                    //测试数据
                    System.out.println("22222222222222222222222222222222222");


                    //从请求的url中获取方法名
                    String methodName = requestURL.substring(requestURL.lastIndexOf("/") + 1);
                    //获取要执行的方法的method对象，另外参数如果是req.getClass()会失败！！！
                    Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                    //暴力反射执行私有方法
                    method.setAccessible(true);
                    //执行方法
                    long time1 = System.currentTimeMillis();
                    method.invoke(this, req, resp);
                    long time2 = System.currentTimeMillis();
                    //获取url
                    syslog(req, time1, time2, methodName);

                }
            }

            req.getRequestDispatcher("/WEB-INF/pages/common/error.jsp").forward(req, resp);
            //然后再跳转到首页

        } catch (Exception e) {
            e.printStackTrace();
        }



//        StringBuffer requestURL = req.getRequestURL();
//
//        //从请求的url中获取方法名
//        String methodName = requestURL.substring(requestURL.lastIndexOf("/") + 1);
//        //获取要执行的方法的method对象，另外参数如果是req.getClass()会失败！！！
//        Method method = null;
//        try {
//            method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        //暴力反射执行私有方法
//        method.setAccessible(true);
//        //执行方法
//        long time1 = System.currentTimeMillis();
//        try {
//            method.invoke(this, req, resp);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        long time2 = System.currentTimeMillis();
//        //记录日志
//        syslog(req, time1, time2, methodName);




    }

    private void syslog(HttpServletRequest req, long time1, long time2, String methodName) {
        //获取uri
        String uri = req.getRequestURI();

//       System.out.println(userIpAddr+".............");
        //获取url
//        StringBuffer requestURL = req.getRequestURL();
        //将string
//        String burl = requestURL.toString();
        String heimatravel = StringUtils.substringAfter(uri, "heimatravel");

        //资源url
        String url = heimatravel.substring(0, (heimatravel.lastIndexOf("/")));
        if (!(uri.contains("/system/syslog"))) {
            // 获取当前时间
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            String visitTimeStr = format.format(date);  //访问时间
            // System.out.println(visitTimeStr);
            // 获取请求ip地址
            String ip = req.getRemoteAddr();
            String userName = (String) req.getSession().getAttribute("username");
            Long executionTime = time2 - time1;//执行所需要的时间
            //封装SysLog类
            SysLog sysLog = new SysLog();
            //访问的时间
            sysLog.setVisitTimeStr(visitTimeStr);
            //访问的用户名
            sysLog.setUsername(userName);
            //访问的时间
            sysLog.setUrl(url);
            //访问ip
            sysLog.setIp(ip);
            //访问耗时
            sysLog.setExecutionTime(executionTime);
            //方法名称
            sysLog.setMethod(methodName);

            //调用业务层方法
            sysLogService.save(sysLog);
        }

    }

}
