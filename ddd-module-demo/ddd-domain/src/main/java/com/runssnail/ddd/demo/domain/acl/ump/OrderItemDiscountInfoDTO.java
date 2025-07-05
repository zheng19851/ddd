
package com.runssnail.ddd.demo.domain.acl.ump;

import com.runssnail.ddd.common.dto.BaseDTO;

/**
 * 订单明细优惠信息
 */
public class OrderItemDiscountInfoDTO extends BaseDTO {

    private DiscountInfoDTO discountInfo;

    public DiscountInfoDTO getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(DiscountInfoDTO discountInfo) {
        this.discountInfo = discountInfo;
    }
}
