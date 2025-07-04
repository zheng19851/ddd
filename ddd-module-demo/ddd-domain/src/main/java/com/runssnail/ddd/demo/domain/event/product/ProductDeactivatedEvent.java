package com.runssnail.ddd.demo.domain.event.product;

import com.runssnail.ddd.common.event.BaseEvent;
import lombok.Getter;

/**
 * @author zhengwei
 * @date 2019-11-05 15:08
 **/
@Getter
public class ProductDeactivatedEvent extends BaseEvent {

    private String productId;

    public ProductDeactivatedEvent(String productId) {
        this.productId = productId;
    }


}
