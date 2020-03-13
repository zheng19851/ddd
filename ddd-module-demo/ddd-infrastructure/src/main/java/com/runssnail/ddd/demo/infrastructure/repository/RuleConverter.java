package com.runssnail.ddd.demo.infrastructure.repository;

import com.runssnail.ddd.converter.Converter;
import com.runssnail.ddd.demo.client.dataobject.RuleDO;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleId;
import com.runssnail.ddd.demo.domain.model.rule.Rule;

import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-05 15:18
 **/
@Component
public class RuleConverter implements Converter<Rule, RuleDO> {

    @Override
    public RuleDO serialize(Rule rule) {

        // todo

        RuleDO ruleDO = new RuleDO();

        return ruleDO;
    }

    @Override
    public Rule deserialize(RuleDO ruleDO) {
        // todo
        return new Rule(new RuleId(ruleDO.getId()));
    }
}
