package com.runssnail.ddd.demo.application.eventhandler;

import org.springframework.stereotype.Component;

import com.runssnail.ddd.demo.domain.event.product.ProductCreatedEvent;
import com.runssnail.ddd.event.BaseEventHandler;

/**
 * @author zhengwei
 * @date 2019-11-05 15:28
 **/
@Component
public class ProductCreatedEventHandler extends BaseEventHandler<ProductCreatedEvent> {

    @Override
    protected void doHandle(ProductCreatedEvent event) {
        log.info("receive a ProductCreatedEvent, id={}", event.getProductId());
    }

    @Override
    public Class<ProductCreatedEvent> supportEventType() {
        return ProductCreatedEvent.class;
    }
}
