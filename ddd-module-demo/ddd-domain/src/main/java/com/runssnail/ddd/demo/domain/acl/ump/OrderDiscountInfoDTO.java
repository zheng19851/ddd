package com.runssnail.ddd.demo.domain.acl.ump;

import com.runssnail.ddd.common.dto.BaseDTO;

import java.util.List;

public class OrderDiscountInfoDTO extends BaseDTO {

    private String orderId;

    private List<OrderItemDiscountInfoDTO> orderItems;

    private DiscountInfoDTO discountInfo;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderItemDiscountInfoDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDiscountInfoDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public DiscountInfoDTO getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(DiscountInfoDTO discountInfo) {
        this.discountInfo = discountInfo;
    }
}
