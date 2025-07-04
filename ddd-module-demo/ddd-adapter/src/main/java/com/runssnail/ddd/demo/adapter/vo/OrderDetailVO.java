package com.runssnail.ddd.demo.adapter.vo;


import lombok.Data;

@Data
public class OrderDetailVO {

    private String id;

    private String title;

    private String skuId;

    private Integer quantity;
}
