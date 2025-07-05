package com.runssnail.ddd.demo.domain.model.order;

import com.runssnail.ddd.common.domain.Entity;
import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.demo.domain.valueobject.Money;
import com.runssnail.ddd.demo.domain.valueobject.order.OrderId;
import com.runssnail.ddd.demo.domain.valueobject.order.OrderItemId;
import com.runssnail.ddd.demo.domain.valueobject.product.ProductId;
import lombok.Data;
import org.apache.commons.lang3.Validate;

/**
 * 例子
 */
@Data
public class OrderItem extends Entity {

    private OrderItemId orderItemId;
    private OrderId orderId;
    private ProductId productId;
    private String productName;
    private Money price;
    private int quantity;

    public OrderItem(OrderItemId orderItemId, OrderId id, ProductId productId, String productName, Money price, int quantity) {

    }

    @Override
    public void validate() throws BizException, IllegalArgumentException {
        Validate.notNull(productId, "productId不能为空");
        Validate.isTrue( quantity > 0 && quantity <= 100, "数量必须[1,100]");
    }
}
