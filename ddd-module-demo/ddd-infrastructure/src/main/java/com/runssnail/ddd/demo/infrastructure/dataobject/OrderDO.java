package com.runssnail.ddd.demo.infrastructure.dataobject;

import lombok.Data;

@Data
public class OrderDO {

    private String id;

    private String title;

    private String skuId;

    private Integer quantity;

}
