package com.runssnail.ddd.demo.domain.repository.order;


import com.runssnail.ddd.demo.domain.model.order.Order;

public interface OrderRepository {

    Order getOrder(String orderId);

    void createOrder(Order order);

    void orderPay(Order order);
}
