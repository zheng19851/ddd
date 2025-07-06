package com.runssnail.ddd.demo.domain.acl.ump;

import com.runssnail.ddd.common.dto.BaseDTO;

import java.util.List;

public class OrderInfoDTO extends BaseDTO {

    private String orderId;

    private List<OrderItemInfoDTO> orderItems;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderItemInfoDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemInfoDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
