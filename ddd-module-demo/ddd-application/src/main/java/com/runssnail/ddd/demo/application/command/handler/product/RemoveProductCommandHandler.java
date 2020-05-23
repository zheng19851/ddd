package com.runssnail.ddd.demo.application.command.handler.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.domain.model.product.Product;
import com.runssnail.ddd.demo.domain.repository.ProductRepository;
import com.runssnail.ddd.demo.client.dto.command.product.RemoveProductCommand;
import com.runssnail.ddd.demo.client.dto.result.product.RemoveProductResult;
import com.runssnail.ddd.demo.domain.event.product.ProductRemovedEvent;
import com.runssnail.ddd.event.EventBus;

/**
 * @author zhengwei
 * @date 2019-11-05 14:50
 **/
@Component
public class RemoveProductCommandHandler extends BaseCommandHandler<RemoveProductCommand, RemoveProductResult> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public Class<RemoveProductCommand> supportCommand() {
        return RemoveProductCommand.class;
    }

    @Override
    public RemoveProductResult doHandle(RemoveProductCommand command) {

        Product product = productRepository.selectById(command.getProductId());

        product.remove(command);

        // 删除
        productRepository.remove(product);

        // 发布领域事件
        eventBus.publish(new ProductRemovedEvent(product.getProductId()));

        RemoveProductResult result = new RemoveProductResult();
        result.setCode(Result.SUCCESS_CODE);
        result.setMessage(Result.SUCCESS_MSG);
        result.setProductId(product.getProductId());
        return result;
    }
}
