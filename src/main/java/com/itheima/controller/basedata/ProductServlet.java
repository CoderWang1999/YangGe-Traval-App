package com.itheima.controller.basedata;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.itheima.controller.BaseServlet;
import com.itheima.domain.basedata.Product;
import com.itheima.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product/*")
public class ProductServlet extends BaseServlet {

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");
        String findLike = req.getParameter("findLike");
        List<Product> list=null;
        if (findLike==null){
            list = productService.findAll(pageNum, pageSize);

        }else {
            list=productService.search(findLike);
            req.setAttribute("search",findLike);
        }

        PageInfo<Product> pageInfo = new PageInfo<>(list);

        req.setAttribute("page", pageInfo);

        req.getRequestDispatcher("/WEB-INF/pages/basedata/product/product-list.jsp").forward(req, resp);

    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productService.list();
        ObjectMapper ojb = new ObjectMapper();
        String s = ojb.writeValueAsString(list);
        resp.getWriter().write(s);
        req.getRequestDispatcher("/WEB-INF/pages/basedata/product/product-add.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = BeanUtil.fillBean(req, Product.class, "yyyy-MM-dd HH:mm");
        productService.save(product);
        findAll(req, resp);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("ids");
        productService.deleteById(ids);
        findAll(req, resp);
    }

    private void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Product product = productService.findById(id);
        req.setAttribute("product", product);
        req.getRequestDispatcher("/WEB-INF/pages/basedata/product/product-update.jsp").forward(req, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = BeanUtil.fillBean(req, Product.class, "yyyy-MM-dd HH:mm");
        productService.update(product);
        findAll(req, resp);
    }

    private void start(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("ids");
        productService.startUpdate(ids);
        findAll(req, resp);
    }

    private void close(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("ids");
        productService.closeUpdate(ids);
        findAll(req, resp);
    }
}
