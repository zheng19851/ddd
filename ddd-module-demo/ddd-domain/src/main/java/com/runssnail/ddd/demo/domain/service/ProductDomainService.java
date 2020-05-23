package com.runssnail.ddd.demo.domain.service;

import com.runssnail.ddd.demo.client.dto.command.product.CreateProductCommand;
import com.runssnail.ddd.demo.domain.model.product.Product;

import org.springframework.stereotype.Component;

import java.util.UUID;

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
        Product product = new Product(this.nextId(), command.getName(), command.getDescription());

        product.validate();
        return product;
    }
}
