package com.runssnail.ddd.demo.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.runssnail.ddd.demo.client.dto.command.product.CreateProductCommand;
import com.runssnail.ddd.demo.domain.model.product.Product;

/**
 * 领域服务
 *
 * @author zhengwei
 * @date 2019-11-06 11:59
 **/
@Component
public class ProductDomainService {

    public String nextId() {
        return UUID.randomUUID().toString();
    }

    public Product createProduct(CreateProductCommand command) {
        Product product = Product.create(this.nextId(), command.getName(), command.getDescription());

        product.validate();
        return product;
    }
}
