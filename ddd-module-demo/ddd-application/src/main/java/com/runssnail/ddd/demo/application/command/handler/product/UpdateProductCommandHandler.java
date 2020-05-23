package com.runssnail.ddd.demo.application.command.handler.product;

import com.runssnail.ddd.command.handler.BaseCommandHandler;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.client.dto.command.product.UpdateProductCommand;
import com.runssnail.ddd.demo.client.dto.result.product.UpdateProductResult;
import com.runssnail.ddd.demo.domain.event.product.ProductUpdatedEvent;
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
public class UpdateProductCommandHandler extends BaseCommandHandler<UpdateProductCommand, UpdateProductResult> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EventBus eventBus;

    @Override
    public UpdateProductResult doHandle(UpdateProductCommand command) {

        // 转换领域对象
        Product product = productRepository.selectById(command.getProductId());
        product.update(command);

        productRepository.update(product);

        // 发布领域事件
        eventBus.publish(new ProductUpdatedEvent(product.getProductId()));

        UpdateProductResult result = new UpdateProductResult();
        result.setCode(Result.SUCCESS_CODE);
        result.setMessage(Result.SUCCESS_MSG);
        result.setProductId(product.getProductId());
        return result;
    }

    @Override
    public Class<UpdateProductCommand> supportCommand() {
        return UpdateProductCommand.class;
    }

}
