package com.runssnail.ddd.demo.application.commandhandling.handler.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.commandhandling.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.command.product.RemoveProductCommand;
import com.runssnail.ddd.demo.domain.event.product.ProductRemovedEvent;
import com.runssnail.ddd.demo.domain.exception.ProductErrorCode;
import com.runssnail.ddd.demo.domain.model.product.Product;
import com.runssnail.ddd.demo.domain.repository.product.ProductRepository;
import com.runssnail.ddd.eventhandling.EventBus;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class RemoveProductCommandHandler extends BaseCommandHandler<RemoveProductCommand, Result> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<RemoveProductCommand> supportCommand() {
        return RemoveProductCommand.class;
    }

    @Override
    public Result doHandle(RemoveProductCommand command) {

        Product product = productRepository.selectById(command.getProductId());
        if (product == null || product.isDeleted()) {
            // 不存在
            return Result.failure(ProductErrorCode.PRODUCT_NOT_EXISTS);
        }

        product.remove(command);

        // 删除
        productRepository.remove(product);

        // 发布领域事件
        eventBus.publish(new ProductRemovedEvent(product.getProductId()));

        return Result.success(product.getProductId());
    }
}
