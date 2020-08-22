package com.itheima.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.controller.BaseServlet;
import com.itheima.domain.system.SysLog;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@WebServlet("/system/syslog/*")
public class SysLogServlet extends BaseServlet {


    //查询
    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求域的内容
        String find = req.getParameter("find");
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");
        if (find == null) {
            List<SysLog> list = sysLogService.findAll(pageNum, pageSize);
            PageInfo<SysLog> pageInfo = new PageInfo<>(list);
// 把list对象存入作用域
            req.setAttribute("page", pageInfo);

        } else {
            List<SysLog> list = sysLogService.findLikeTime(find, pageNum, pageSize);
            PageInfo<SysLog> pageInfo = new PageInfo<>(list);
            req.setAttribute("find", find);
            req.setAttribute("page", pageInfo);


        }
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/log/syslog-list.jsp").forward(req, resp);
    }

    private void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String ids = req.getParameter("ids");
//      System.out.println(ids+"============");

        boolean flag = sysLogService.deleteById(ids);
        if (flag) {
            findAll(req, resp);
        } else {
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }

    }

    private void downloadReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //返回的数据类型为文件xlsx类型 并指定字符集
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");

//当把文件命名为中文时需要的设置文件名转换为字节 然后指定 编码iso8859-1
        String fileName = new String("测试文件名.xlsx".getBytes(), "iso8859-1");
        //设置文件下载的名称+fileName为中文后缀
        resp.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        // 文件直接明明为英文
//    response.addHeader("Content-Disposition","attachment;fileName=text.xlsx"
        //生成报告的文件 传递到前端页面
        ByteArrayOutputStream os = sysLogService.getReport();

        //响应流给前台数据
        ServletOutputStream sos = resp.getOutputStream();

        //将os流传递给sos响应流对象 writeTo()方法;
        os.writeTo(sos);
        //将响应流数据刷新出去
        sos.flush();
//关闭ByteArrayOutputStream流
        os.close();

    }

//    private void  findLikeTime(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
}
