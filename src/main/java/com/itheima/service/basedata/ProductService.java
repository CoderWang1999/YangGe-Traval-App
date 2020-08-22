package com.itheima.service.basedata;

import com.itheima.domain.basedata.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll(String pageNum, String pageSize);

    List<Product> list();

    void deleteById(String ids);

    void save(Product product);

    Product findById(String id);

    void update(Product product);

    void startUpdate(String ids);

    void closeUpdate(String ids);

    List<Product> search(String findLike);
}
