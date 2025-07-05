package com.runssnail.ddd.demo.application.commandhandling.handler.order;

import com.runssnail.ddd.commandhandling.handler.BaseCommandHandler;
import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.application.command.order.OrderPayCommand;
import com.runssnail.ddd.demo.domain.entity.order.Order;
import com.runssnail.ddd.demo.domain.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderPayCommandHandler extends BaseCommandHandler<OrderPayCommand, Result> {

    @Autowired
    OrderRepository repository;

    @Override
    protected Result<String> doHandle(OrderPayCommand cmd) throws BizException {

        // 查询订单
        Order order = repository.getOrder(cmd.getOrderId());
        order.pay();

        // 保存订单
        repository.orderPay(order);

        // 发布事件

        return Result.success(order.getId().getValue().toString());
    }

    @Override
    public Class<OrderPayCommand> supportCommand() {
        return OrderPayCommand.class;
    }
}
