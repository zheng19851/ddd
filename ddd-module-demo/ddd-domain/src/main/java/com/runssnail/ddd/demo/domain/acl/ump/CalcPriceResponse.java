package com.runssnail.ddd.demo.domain.acl.ump;

import com.runssnail.ddd.common.dto.BaseDTO;

public class CalcPriceResponse extends BaseDTO {

    /**
     * 订单
     */
    private OrderDiscountInfoDTO orderDiscountInfo;

    public OrderDiscountInfoDTO getOrderDiscountInfo() {
        return orderDiscountInfo;
    }

    public void setOrderDiscountInfo(OrderDiscountInfoDTO orderDiscountInfo) {
        this.orderDiscountInfo = orderDiscountInfo;
    }
}
