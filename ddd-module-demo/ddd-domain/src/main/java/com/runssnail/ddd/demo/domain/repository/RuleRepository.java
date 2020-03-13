package com.runssnail.ddd.demo.domain.repository;

import com.runssnail.ddd.demo.client.dto.domain.rule.RuleId;
import com.runssnail.ddd.demo.domain.model.rule.Rule;

/**
 * @author zhengwei
 * @date 2019-11-05 14:30
 **/
public interface RuleRepository {

    Rule selectById(RuleId ruleId);

    void saveRule(Rule rule);

    RuleId nextId();
}
