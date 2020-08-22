package com.itheima.mapper.basedata;

import com.itheima.domain.basedata.Product;

import java.util.List;

public interface ProductMapper {
    //查询所有
    List<Product> findAll();

    void deleteById(String[] id);

    void save(Product product);

    Product findById(String id);

    void update(Product product);

    void startUpdate(String[] id);

    void closeUpdate(String[] id);

    List<Product> search(String like);
}
