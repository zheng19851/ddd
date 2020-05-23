package com.runssnail.ddd.demo.application.command.handler.product;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.product.ActivateProductCommand;
import com.runssnail.ddd.demo.client.dto.result.product.ActivateProductResult;
import com.runssnail.ddd.demo.domain.event.product.ProductDeactivatedEvent;
import com.runssnail.ddd.demo.domain.model.product.Product;
import com.runssnail.ddd.demo.domain.repository.ProductRepository;
import com.runssnail.ddd.event.EventBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class ActivateProductCommandHandler extends BaseCommandHandler<ActivateProductCommand, ActivateProductResult> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<ActivateProductCommand> supportCommand() {
        return ActivateProductCommand.class;
    }

    @Override
    public ActivateProductResult doHandle(ActivateProductCommand command) {

        Product product = productRepository.selectById(command.getProductId());

        // 启用
        product.activate(command);

        // 保存数据
        productRepository.activate(product);

        // 发布领域事件
        eventBus.publish(new ProductDeactivatedEvent(product.getProductId()));

        ActivateProductResult result = new ActivateProductResult();
        result.setCode(Result.SUCCESS_CODE);
        result.setMessage(Result.SUCCESS_MSG);
        result.setProductId(product.getProductId());
        return result;
    }
}
