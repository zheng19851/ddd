package com.runssnail.ddd.demo.domain.event.product;

import com.runssnail.ddd.common.event.AbstractEvent;
import lombok.Getter;

/**
 * @author zhengwei
 * @date 2019-11-05 15:08
 **/
@Getter
public class ProductUpdatedEvent extends AbstractEvent {

    private String productId;

    public ProductUpdatedEvent(String productId) {
        this.productId = productId;
    }

}
