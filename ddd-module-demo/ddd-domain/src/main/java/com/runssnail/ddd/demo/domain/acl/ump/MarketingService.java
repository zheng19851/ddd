package com.runssnail.ddd.demo.domain.acl.ump;


/**
 * 营销服务
 */
public interface MarketingService {

    CalcPriceResponse calcPrice(CalcPriceRequest req);
}
