package com.runssnail.ddd.demo.domain.entity.order;

import com.runssnail.ddd.common.domain.Entity;
import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.demo.domain.valueobject.*;
import lombok.Data;
import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 订单
 */
@Data
public class Order extends Entity {

    private final OrderId id;

    private String title;

    private Long buyerId;

    private Long sellerId;

    private List<OrderItem> items;

    private Money totalAmount;

    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 可以包含领域层业务规则


    /**
     * 校验订单信息
     *
     * @throws BizException
     */
    @Override
    public void validate() throws BizException {
        Validate.notNull(id, "订单id不能为空");
        Validate.notBlank(title, "订单标题不能为空");
        Validate.notNull(buyerId, "买家ID不能为null");
        Validate.notNull(sellerId, "卖家ID不能为null");
        Validate.isTrue(!buyerId.equals(sellerId), "不能自卖自买");// 防止刷单
        Validate.isTrue(title.length() <= 200, "订单标题不能超过200个字符");

    }

    public void addItem(ProductId productId, String productName, Money price, int quantity) {
        OrderItem item = new OrderItem(
                new OrderItemId(UUID.randomUUID()),
                this.id,
                productId,
                productName,
                price,
                quantity
        );
        items.add(item);
        calculateTotalAmount();
    }

    public void confirm() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("订单状态不是已创建，无法确认");
        }
        this.status = OrderStatus.CONFIRMED;
        this.updatedAt = LocalDateTime.now();
    }

    public void pay() {
        if (status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("订单状态不是已确认，无法支付");
        }
        this.status = OrderStatus.PAID;
        this.updatedAt = LocalDateTime.now();
    }

    public void ship() {
        if (status != OrderStatus.PAID) {
            throw new IllegalStateException("订单状态不是已支付，无法发货");
        }
        this.status = OrderStatus.SHIPPED;
        this.updatedAt = LocalDateTime.now();
    }

    public void deliver() {
        if (status != OrderStatus.SHIPPED) {
            throw new IllegalStateException("订单状态不是已发货，无法确认送达");
        }
        this.status = OrderStatus.DELIVERED;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancel() {
        if (status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("订单已送达，无法取消");
        }
        this.status = OrderStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }

    private void calculateTotalAmount() {
        this.totalAmount = items.stream()
                .map(item -> item.getPrice().multiply(item.getQuantity()))
                .reduce(Money.ZERO, Money::add);
    }

}
