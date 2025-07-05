package com.runssnail.ddd.demo.domain.valueobject.product;


import com.runssnail.ddd.common.domain.ValueObject;
import org.apache.commons.lang3.Validate;

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
