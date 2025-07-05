package com.runssnail.ddd.demo.application.commandhandling.handler.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.commandhandling.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.command.product.ActivateProductCommand;
import com.runssnail.ddd.demo.domain.event.product.ProductActivatedEvent;
import com.runssnail.ddd.demo.domain.exception.ProductErrorCode;
import com.runssnail.ddd.demo.domain.entity.product.Product;
import com.runssnail.ddd.demo.domain.repository.product.ProductRepository;
import com.runssnail.ddd.eventhandling.EventBus;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class ActivateProductCommandHandler extends BaseCommandHandler<ActivateProductCommand, Result> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<ActivateProductCommand> supportCommand() {
        return ActivateProductCommand.class;
    }

    @Override
    public Result doHandle(ActivateProductCommand command) {

        Product product = productRepository.selectById(command.getProductId());
        if (product == null || product.isDeleted()) {
            // 不存在
            return Result.failure(ProductErrorCode.PRODUCT_NOT_EXISTS);
        }
        // 启用
        product.activate(command);

        // 保存数据
        productRepository.activate(product);

        // 发布领域事件
        eventBus.publish(new ProductActivatedEvent(product.getProductId()));

        return Result.success(product.getProductId());
    }
}
