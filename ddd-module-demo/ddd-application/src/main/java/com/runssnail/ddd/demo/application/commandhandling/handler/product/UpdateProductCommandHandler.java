package com.runssnail.ddd.demo.application.commandhandling.handler.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.commandhandling.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.command.product.UpdateProductCommand;
import com.runssnail.ddd.demo.domain.event.product.ProductUpdatedEvent;
import com.runssnail.ddd.demo.domain.exception.ProductErrorCode;
import com.runssnail.ddd.demo.domain.model.product.Product;
import com.runssnail.ddd.demo.domain.repository.product.ProductRepository;
import com.runssnail.ddd.eventhandling.EventBus;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class UpdateProductCommandHandler extends BaseCommandHandler<UpdateProductCommand, Result> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public Result doHandle(UpdateProductCommand command) {

        // 转换领域对象
        Product product = productRepository.selectById(command.getProductId());
        if (product == null || product.isDeleted()) {
            // 不存在
            return Result.failure(ProductErrorCode.PRODUCT_NOT_EXISTS);
        }

        product.update(command);

        productRepository.update(product);

        // 发布领域事件
        eventBus.publish(new ProductUpdatedEvent(product.getProductId()));

        return Result.success(product.getProductId());
    }

    @Override
    public Class<UpdateProductCommand> supportCommand() {
        return UpdateProductCommand.class;
    }

}
