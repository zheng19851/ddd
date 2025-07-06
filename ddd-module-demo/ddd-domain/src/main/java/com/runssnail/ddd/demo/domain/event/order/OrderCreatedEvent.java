package com.runssnail.ddd.demo.domain.event.order;

import com.runssnail.ddd.common.event.BaseEvent;

public class OrderCreatedEvent extends BaseEvent {

    private String orderId;

    public OrderCreatedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
