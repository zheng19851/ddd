package com.runssnail.ddd.demo.domain.service;

import com.runssnail.ddd.demo.client.dto.domain.rule.RuleId;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 规则领域服务
 *
 * @author zhengwei
 * @date 2019-11-06 12:00
 **/
@Component
public class RuleDomainService {

    public RuleId nextId() {
        return new RuleId(UUID.randomUUID().toString());
    }
}
