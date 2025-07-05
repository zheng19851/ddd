package com.runssnail.ddd.demo.domain.valueobject.order;


import com.runssnail.ddd.common.domain.ValueObject;

import java.util.UUID;

public class OrderItemId extends ValueObject {
    private final UUID value;

    public OrderItemId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemId orderId = (OrderItemId) o;
        return value.equals(orderId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}