package com.runssnail.ddd.demo.infrastructure.repository;

import com.runssnail.ddd.demo.client.dataobject.ProductDO;
import com.runssnail.ddd.demo.client.dto.domain.product.ProductId;
import com.runssnail.ddd.demo.domain.model.product.Product;
import com.runssnail.ddd.demo.domain.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author zhengwei
 * @date 2019-11-05 15:14
 **/
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductConverter productConverter;

    @Override
    public void save(Product product) {

        ProductDO productDO = productConverter.serialize(product);

        // todo 调用mybatis mapper保存数据对象
    }

    @Override
    public Product selectById(String id) {

        // todo 调用mybatis mapper查询数据对象
        return new Product(id);
    }

    @Override
    public void update(Product product) {

        ProductDO productDO = productConverter.serialize(product);

        // todo 调用mybatis mapper修改数据对象
    }

    @Override
    public ProductId nextId() {
        return ProductId.create(UUID.randomUUID().toString());
    }

    @Override
    public void deactivate(Product product) {
        // todo 调用mybatis mapper修改数据对象
    }

    @Override
    public void activate(Product product) {

    }

    @Override
    public void remove(Product product) {

    }
}
