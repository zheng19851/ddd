package com.runssnail.ddd.demo.domain.event.product;

import com.runssnail.ddd.common.event.AbstractEvent;
import com.runssnail.ddd.demo.client.dto.domain.product.ProductId;

import lombok.Getter;

/**
 * @author zhengwei
 * @date 2019-11-05 15:08
 **/
@Getter
public class ProductActivatedEvent extends AbstractEvent {

    private ProductId productId;

    public ProductActivatedEvent(ProductId productId) {
        this.productId = productId;
    }


}
