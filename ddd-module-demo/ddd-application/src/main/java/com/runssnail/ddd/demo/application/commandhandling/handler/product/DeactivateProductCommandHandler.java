package com.runssnail.ddd.demo.application.commandhandling.handler.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.commandhandling.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.product.DeactivateProductCommand;
import com.runssnail.ddd.demo.domain.event.product.ProductDeactivatedEvent;
import com.runssnail.ddd.demo.domain.exception.ProductErrorCode;
import com.runssnail.ddd.demo.domain.entity.product.Product;
import com.runssnail.ddd.demo.domain.repository.product.ProductRepository;
import com.runssnail.ddd.eventhandling.EventBus;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class DeactivateProductCommandHandler extends BaseCommandHandler<DeactivateProductCommand, Result> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<DeactivateProductCommand> supportCommand() {
        return DeactivateProductCommand.class;
    }

    @Override
    public Result<String> doHandle(DeactivateProductCommand command) {

        Product product = productRepository.selectById(command.getProductId());
        if (product == null || product.isDeleted()) {
            // 不存在
            return Result.failure(ProductErrorCode.PRODUCT_NOT_EXISTS);
        }

        // 禁用
        product.deactivate(command);

        // 保存数据
        productRepository.deactivate(product);

        // 发布领域事件
        eventBus.publish(new ProductDeactivatedEvent(product.getProductId()));

        return Result.success(product.getProductId());
    }
}
