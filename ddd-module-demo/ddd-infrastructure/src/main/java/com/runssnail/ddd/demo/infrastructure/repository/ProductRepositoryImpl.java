package com.runssnail.ddd.demo.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.runssnail.ddd.common.result.PagingResult;
import com.runssnail.ddd.demo.client.dataobject.ProductDO;
import com.runssnail.ddd.demo.client.dto.domain.product.ProductId;
import com.runssnail.ddd.demo.domain.model.product.Product;
import com.runssnail.ddd.demo.domain.repository.ProductQuery;
import com.runssnail.ddd.demo.domain.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengwei
 * @date 2019-11-05 15:14
 **/
@Repository
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductConverter productConverter;

    @Override
    public void save(Product product) {

        ProductDO productDO = productConverter.serialize(product);

        // todo 调用mybatis mapper保存数据对象
        log.info("save domain, id={}", product.getProductId());
    }

    @Override
    public Product selectById(String id) {

        // todo 调用mybatis mapper查询数据对象
        Product product = new Product(id);
        return product.name("test").description("test description");
    }

    @Override
    public void update(Product product) {

        ProductDO productDO = productConverter.serialize(product);

        // todo 调用mybatis mapper修改数据对象
        log.info("update domain, id={}", product.getProductId());
    }

    @Override
    public ProductId nextId() {
        return ProductId.create(UUID.randomUUID().toString());
    }

    @Override
    public void deactivate(Product product) {
        // todo 调用mybatis mapper修改数据对象
        log.info("deactivate domain, id={}", product.getProductId());
    }

    @Override
    public void activate(Product product) {
        log.info("activate domain, id={}", product.getProductId());
    }

    @Override
    public void remove(Product product) {
        log.info("remove domain, id={}", product.getProductId());
    }

    @Override
    public PagingResult<Product> queryProducts(ProductQuery query) {
        log.info("query products, query={}", query);

        Product product = new Product("1", "test", "test description");
        List<Product> products = Lists.newArrayList(product);
        return PagingResult.create(products, 1, 1);
    }
}
