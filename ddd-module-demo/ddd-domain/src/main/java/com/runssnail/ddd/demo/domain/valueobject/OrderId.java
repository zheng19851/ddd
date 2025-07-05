package com.runssnail.ddd.demo.domain.valueobject;


import com.runssnail.ddd.common.domain.ValueObject;

import java.util.UUID;

public class OrderId extends ValueObject {
    private final String value;

    public OrderId(UUID value) {
        this.value = value.toString();
    }

    public OrderId() {
        this.value = UUID.randomUUID().toString();
    }

    public OrderId(String orderId) {
        this.value = orderId;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderId orderId = (OrderId) o;
        return value.equals(orderId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}