package com.runssnail.ddd.demo.domain.model.product;

import org.apache.commons.lang3.Validate;

import com.runssnail.ddd.common.domain.Entity;
import com.runssnail.ddd.demo.client.dto.command.product.ActivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.DeactivateProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.RemoveProductCommand;
import com.runssnail.ddd.demo.client.dto.command.product.UpdateProductCommand;

import lombok.Getter;

/**
 * @author zhengwei
 * @date 2019-11-05 14:20
 **/
@Getter
public class Product extends Entity {

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

    public Product() {

    }

    public Product(String productId, String name, String description) {
        this.productId = productId;
        this.name = name;
        this.description = description;
    }

    public Product(String productId) {
        this.productId = productId;
    }

    public static Product create(String productId, String name, String description) {
        return new Product(productId, name, description);
    }

    public Product name(String name) {
        Validate.notBlank(name);
        this.name = name;
        return this;
    }

    public Product description(String description) {
        Validate.notBlank(description);
        this.description = description;
        return this;
    }

    public void remove(RemoveProductCommand command) {
        // todo 判断删除
        this.deleted = true;
        this.operator = command.getOperator();
    }

    public void deactivate(DeactivateProductCommand command) {
        // todo 判断能否禁用？
        this.status = 0;
        this.operator = command.getOperator();
    }

    public void activate(ActivateProductCommand command) {
        // todo 判断能否启用？
        this.status = 1;
        this.operator = command.getOperator();
    }

    public void update(UpdateProductCommand command) {
        this.name(command.getName()).description(command.getDescription());
    }

}
