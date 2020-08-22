package com.itheima.service.impl.basedata;

import com.github.pagehelper.PageHelper;
import com.itheima.domain.basedata.Product;
import com.itheima.mapper.basedata.ProductMapper;
import com.itheima.service.basedata.ProductService;
import com.itheima.utils.MapperUtil;
import com.itheima.utils.TransactionUtil;
import com.itheima.utils.UidUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findAll(String pageNum,String pageSize) {
        //用工具类获取session对象
        SqlSession session = MapperUtil.getSession();
        //获取mapper对象
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        //
        int pageN=1;
        int pageS=5;
        if (StringUtils.isNotBlank(pageNum)){
            pageN=Integer.parseInt(pageNum);
        }
        if (StringUtils.isNotBlank(pageSize)){
            pageS=Integer.parseInt(pageSize);
        }
        PageHelper.startPage(pageN,pageS);
        List<Product> list=mapper.findAll();
        //通过工具类事务提交释放资源
        TransactionUtil.close(session);
        return list;
    }

    @Override
    public List<Product> list() {
        SqlSession session = MapperUtil.getSession();
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        List<Product> list = mapper.findAll();
        return list;
    }

    @Override
    public void deleteById(String ids) {
        SqlSession session = MapperUtil.getSession();
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        String[] id = ids.split(",");
        mapper.deleteById(id);
        MapperUtil.close(session);
    }

    @Override
    public void save(Product product) {
        SqlSession session = MapperUtil.getSession();
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        String uuid = UidUtil.getUid();
        product.setId(uuid);
        mapper.save(product);
        MapperUtil.close(session);
    }

    @Override
    public Product findById(String id) {
        SqlSession session = MapperUtil.getSession();
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        Product product=mapper.findById(id);
        MapperUtil.close(session);
        return product;
    }

    @Override
    public void update(Product product) {
        SqlSession session = MapperUtil.getSession();
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        mapper.update(product);
        MapperUtil.close(session);
    }

    @Override
    public void startUpdate(String ids) {
        SqlSession session = MapperUtil.getSession();
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        String[] id = ids.split(",");
        mapper.startUpdate(id);
        MapperUtil.close(session);
    }

    @Override
    public void closeUpdate(String ids) {
        SqlSession session = MapperUtil.getSession();
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        String[] id = ids.split(",");
        mapper.closeUpdate(id);
        MapperUtil.close(session);
    }

    @Override
    public List<Product> search(String findLike) {
        SqlSession session = MapperUtil.getSession();
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        String word= new StringBuilder("%").append(findLike).append("%").toString();
        List<Product> list=mapper.search(word);
        return list;
    }
}

