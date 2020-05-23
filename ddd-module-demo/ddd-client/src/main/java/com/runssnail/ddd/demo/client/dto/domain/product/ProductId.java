package com.runssnail.ddd.demo.client.dto.domain.product;

import org.apache.commons.lang3.Validate;

import com.runssnail.ddd.common.domain.ValueObject;
import lombok.Data;

/**
 * @author zhengwei
 * @date 2019-11-05 14:30
 **/
@Data
public class ProductId extends ValueObject {
    private String id;

    protected ProductId(String id) {
        Validate.notBlank(id);
        this.id = id;
    }

    public static ProductId create(String id) {
        return new ProductId(id);
    }
}
