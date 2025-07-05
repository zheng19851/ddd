package com.runssnail.ddd.demo.domain.event.order;

import com.runssnail.ddd.common.event.BaseEvent;

public class OrderCreatedEvent extends BaseEvent {

    private String order;

    public OrderCreatedEvent(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
