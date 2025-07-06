package com.runssnail.ddd.demo.domain.acl.ump;

import com.runssnail.ddd.common.dto.BaseDTO;

public class CalcPriceRequest extends BaseDTO {

    private OrderInfoDTO orderInfo;

    public CalcPriceRequest(OrderInfoDTO orderInfo) {
        this.orderInfo = orderInfo;
    }

    public OrderInfoDTO getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoDTO orderInfo) {
        this.orderInfo = orderInfo;
    }
}
