package com.runssnail.ddd.demo.application.command.handler.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.domain.model.product.Product;
import com.runssnail.ddd.demo.domain.repository.ProductRepository;
import com.runssnail.ddd.demo.client.dto.command.product.DeactivateProductCommand;
import com.runssnail.ddd.demo.client.dto.result.product.DeactivateProductResult;
import com.runssnail.ddd.demo.domain.event.product.ProductDeactivatedEvent;
import com.runssnail.ddd.event.EventBus;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class DeactivateProductCommandHandler extends BaseCommandHandler<DeactivateProductCommand, DeactivateProductResult> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<DeactivateProductCommand> supportCommand() {
        return DeactivateProductCommand.class;
    }

    @Override
    public DeactivateProductResult doHandle(DeactivateProductCommand command) {

        Product product = productRepository.selectById(command.getProductId());

        // 禁用
        product.deactivate(command);

        // 保存数据
        productRepository.deactivate(product);

        // 发布领域事件
        eventBus.publish(new ProductDeactivatedEvent(product.getProductId()));

        DeactivateProductResult result = new DeactivateProductResult();
        result.setCode(Result.SUCCESS_CODE);
        result.setMessage(Result.SUCCESS_MSG);
        result.setProductId(product.getProductId());
        return result;
    }
}
