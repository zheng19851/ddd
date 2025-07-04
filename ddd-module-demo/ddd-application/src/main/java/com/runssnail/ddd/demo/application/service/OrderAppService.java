package com.runssnail.ddd.demo.application.service;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.application.command.order.CreateOrderCommand;
import com.runssnail.ddd.demo.application.command.order.OrderPayCommand;
import com.runssnail.ddd.demo.application.commandhandling.handler.order.CreateOrderCommandHandler;
import com.runssnail.ddd.demo.application.commandhandling.handler.order.OrderPayCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAppService {

    @Autowired
    CreateOrderCommandHandler createOrderCommandHandler;

    @Autowired
    OrderPayCommandHandler orderPayCommandHandler;


    public Result<String> createOrder(CreateOrderCommand cmd) {
        return createOrderCommandHandler.handle(cmd);
    }

    public Result<String> orderPay(OrderPayCommand cmd) {
        return orderPayCommandHandler.handle(cmd);
    }
}
