package com.runssnail.ddd.demo.adapter.vo;

import lombok.Data;

@Data
public class CreateOrderVO {

    private Long uid;
    private String skuId;
    private Integer skuQuantity;
}
