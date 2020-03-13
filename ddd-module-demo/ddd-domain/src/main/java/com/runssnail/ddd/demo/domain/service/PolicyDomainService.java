package com.runssnail.ddd.demo.domain.service;

import com.runssnail.ddd.demo.client.dto.domain.policy.PolicyId;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 策略领域服务
 *
 * @author zhengwei
 * @date 2019-11-06 11:59
 **/
@Component
public class PolicyDomainService {

    public PolicyId nextId() {
        return PolicyId.create(UUID.randomUUID().toString());
    }
}
