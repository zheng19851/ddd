package com.runssnail.ddd.demo.client.dto;

import com.runssnail.ddd.common.dto.BaseDTO;

import lombok.Data;

/**
 * @author zhengwei
 */
@Data
public class ProductDTO extends BaseDTO {

    private String productId;

    private String name;

    private String description;

    private Integer status;

    private boolean deleted;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 最近操作人
     */
    private String operator;
}
