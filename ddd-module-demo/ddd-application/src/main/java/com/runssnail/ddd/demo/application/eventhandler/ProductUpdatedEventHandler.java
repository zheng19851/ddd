package com.runssnail.ddd.demo.application.eventhandler;

import org.springframework.stereotype.Component;

import com.runssnail.ddd.demo.domain.event.product.ProductUpdatedEvent;
import com.runssnail.ddd.event.BaseEventHandler;

/**
 * @author zhengwei
 * @date 2019-11-05 15:28
 **/
@Component
public class ProductUpdatedEventHandler extends BaseEventHandler<ProductUpdatedEvent> {

    @Override
    protected void doHandle(ProductUpdatedEvent event) {
        log.info("receive a ProductUpdatedEvent, id={}", event.getProductId());
    }

    @Override
    public Class<ProductUpdatedEvent> supportEventType() {
        return ProductUpdatedEvent.class;
    }
}
