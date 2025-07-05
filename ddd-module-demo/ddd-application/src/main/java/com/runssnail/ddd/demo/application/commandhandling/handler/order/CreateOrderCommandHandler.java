package com.runssnail.ddd.demo.application.commandhandling.handler.order;

import com.runssnail.ddd.commandhandling.handler.BaseCommandHandler;
import com.runssnail.ddd.common.exception.BizException;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.application.command.order.CreateOrderCommand;
import com.runssnail.ddd.demo.domain.acl.product.ProductService;
import com.runssnail.ddd.demo.domain.acl.ump.CalcPriceRequest;
import com.runssnail.ddd.demo.domain.acl.ump.CalcPriceResponse;
import com.runssnail.ddd.demo.domain.acl.ump.MarketingService;
import com.runssnail.ddd.demo.domain.acl.ump.OrderDiscountInfoDTO;
import com.runssnail.ddd.demo.domain.entity.order.Order;
import com.runssnail.ddd.demo.domain.event.order.OrderCreatedEvent;
import com.runssnail.ddd.demo.domain.repository.order.OrderRepository;
import com.runssnail.ddd.demo.domain.service.OrderDomainService;
import com.runssnail.ddd.demo.domain.valueobject.Money;
import com.runssnail.ddd.demo.domain.valueobject.OrderId;
import com.runssnail.ddd.demo.domain.valueobject.ProductId;
import com.runssnail.ddd.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderCommandHandler extends BaseCommandHandler<CreateOrderCommand, Result> {

    @Autowired
    OrderRepository repository;

    @Autowired
    OrderDomainService orderDomainService;

    @Autowired
    EventBus eventBus;

    @Autowired
    ProductService productService;

    @Autowired
    MarketingService marketingService;

    @Override
    protected Result<String> doHandle(CreateOrderCommand cmd) throws BizException {

        // 1、订单号
        String orderId = orderDomainService.nextId();

        // 2、锁库存，这里模拟订单域，访问商品域(正常情况应该是多个sku，因为可以购物车下单，这里只是为了举例，我们就1个sku)
        productService.lockStock(orderId, cmd.getSkuId(), cmd.getSkuQuantity());

        // 3、计算优惠、锁券等，这里要把订单信息推给营销来计算优惠价格、同时锁券等
        CalcPriceResponse calcPriceResponse = marketingService.calcPrice(new CalcPriceRequest(orderId));

        // 4、创单，需要将优惠信息，保存到订单里
        Order order = createOrder(cmd, orderId, calcPriceResponse.getOrderDiscountInfo());
        repository.createOrder(order);

        // 5、发布事件
        eventBus.publish(new OrderCreatedEvent(order.getId().getValue()));

        return Result.success(order.getId().getValue());
    }

    /**
     * 创建订单实体
     *
     * @param cmd               命令
     * @param orderId
     * @param orderDiscountInfo
     * @return
     */
    private Order createOrder(CreateOrderCommand cmd, String orderId, OrderDiscountInfoDTO orderDiscountInfo) {
        ProductId productId = null;
        String productName = "";
        Money price = null;
        int quantity = 1;
        Order order = new Order(new OrderId(orderId));
        order.addItem(productId, productName, price, quantity);
        return order;
    }

    @Override
    public Class<CreateOrderCommand> supportCommand() {
        return CreateOrderCommand.class;
    }
}
