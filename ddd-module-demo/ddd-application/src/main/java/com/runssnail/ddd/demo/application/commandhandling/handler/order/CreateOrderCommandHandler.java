package com.runssnail.ddd.demo.application.commandhandling.handler.order;

import com.runssnail.ddd.commandhandling.handler.BaseCommandHandler;
import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.application.command.order.CreateOrderCommand;
import com.runssnail.ddd.demo.domain.entity.order.Order;
import com.runssnail.ddd.demo.domain.repository.order.OrderRepository;
import com.runssnail.ddd.demo.domain.valueobject.Money;
import com.runssnail.ddd.demo.domain.valueobject.OrderId;
import com.runssnail.ddd.demo.domain.valueobject.ProductId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderCommandHandler extends BaseCommandHandler<CreateOrderCommand, Result> {

    @Autowired
    OrderRepository repository;

    @Override
    protected Result<String> doHandle(CreateOrderCommand cmd) throws BizException {

        Order order = createOrder(cmd);
        repository.createOrder(order);

        return Result.success(order.getId().getValue().toString());
    }

    /**
     * 创建订单实体
     *
     * @param cmd 命令
     * @return
     */
    private Order createOrder(CreateOrderCommand cmd) {
        ProductId productId = null;
        String productName = "";
        Money price = null;
        int quantity = 1;
        Order order = new Order(new OrderId());
        order.addItem(productId, productName, price, quantity);
        return order;
    }

    @Override
    public Class<CreateOrderCommand> supportCommand() {
        return CreateOrderCommand.class;
    }
}
