package com.runssnail.ddd.demo.domain.service;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 领域服务
 *
 * @author zhengwei
 * @date 2019-11-06 11:59
 **/
@Component
public class OrderDomainService {

    public String nextId() {
        return UUID.randomUUID().toString();
    }

}
