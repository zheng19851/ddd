package com.runssnail.ddd.demo.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.runssnail.ddd.command.CommandBus;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.product.CreateProductCommand;
import com.runssnail.ddd.demo.domain.model.product.Product;

/**
 * @author zhengwei
 * @date 2019-11-05 14:38
 **/
@Component
public class ProductApplicationService {

    @Autowired
    private CommandBus commandBus;

    /**
     * 创建商品
     *
     * @param command
     * @return
     */
    @Transactional
    public Result<Product> createProduct(CreateProductCommand command) {
        return commandBus.dispatch(command);
    }
}
