package com.runssnail.ddd.demo.domain.repository;

import com.runssnail.ddd.common.result.PagingResult;
import com.runssnail.ddd.demo.client.dto.domain.product.ProductId;
import com.runssnail.ddd.demo.domain.model.product.Product;

/**
 * @author zhengwei
 * @date 2019-11-05 14:29
 **/
public interface ProductRepository {

    void save(Product product);

    Product selectById(String id);

    void update(Product product);

    /**
     * 存储实现id
     *
     * @return
     */
    ProductId nextId();

    /**
     * 禁用
     *
     * @param product
     */
    void deactivate(Product product);

    void activate(Product product);

    void remove(Product product);

    PagingResult<Product> queryProducts(ProductQuery query);

}
