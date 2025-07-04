package com.runssnail.ddd.demo.adapter.rest;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.adapter.vo.CreateOrderVO;
import com.runssnail.ddd.demo.adapter.vo.OrderPayVO;
import com.runssnail.ddd.demo.application.command.order.CreateOrderCommand;
import com.runssnail.ddd.demo.application.command.order.OrderPayCommand;
import com.runssnail.ddd.demo.application.service.OrderAppService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderAppService orderAppService;
//
//    @GetMapping(path = "/detail/{orderId}", produces = "application/json")
//    public OrderDetailVO getOrderDetail(@PathVariable String orderId) {
//        OrderDetailVO orderVO = new OrderDetailVO();
//
//        Result<OrderDTO> result = orderAppService.getOrderDetail(new GetOrderDetailCommand(orderId));
//
//        OrderDTO orderDTO = result.getData();
//        // 将orderDTO转换成orderVO
//        BeanUtils.copyProperties(orderDTO, orderVO);
//        return orderVO;
//    }

    @PostMapping("/create")
    public String createOrder(CreateOrderVO createOrderVO) {

        CreateOrderCommand cmd = new CreateOrderCommand();
        BeanUtils.copyProperties(createOrderVO, cmd);
        Result<String> result = orderAppService.createOrder(cmd);

        // 将orderDTO转换成orderVO
        return result.getData();
    }

    @PostMapping("/pay")
    public String orderPay(OrderPayVO orderPayVO) {

        OrderPayCommand cmd = new OrderPayCommand();
        BeanUtils.copyProperties(orderPayVO, cmd);
        Result<String> result = orderAppService.orderPay(cmd);

        // 将orderDTO转换成orderVO
        return result.getData();
    }
}
