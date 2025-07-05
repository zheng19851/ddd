package com.runssnail.ddd.demo.domain.acl.ump;

import com.runssnail.ddd.common.dto.BaseDTO;

import java.util.List;

public class OrderDiscountInfoDTO extends BaseDTO {

    private List<OrderItemDiscountInfoDTO> orderItems;

    private DiscountInfoDTO discountInfo;

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
