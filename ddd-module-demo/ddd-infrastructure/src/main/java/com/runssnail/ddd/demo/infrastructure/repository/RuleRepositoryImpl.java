package com.runssnail.ddd.demo.infrastructure.repository;

import com.runssnail.ddd.demo.client.dataobject.RuleDO;
import com.runssnail.ddd.demo.client.dto.domain.rule.RuleId;
import com.runssnail.ddd.demo.domain.model.rule.Rule;
import com.runssnail.ddd.demo.domain.repository.RuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author zhengwei
 * @date 2019-11-05 16:30
 **/
@Repository
public class RuleRepositoryImpl implements RuleRepository {

    @Autowired
    private RuleConverter ruleConverter;

    @Override
    public Rule selectById(RuleId ruleId) {

        RuleDO ruleDO = selectByIdFromDB(ruleId.getId());
        return this.ruleConverter.deserialize(ruleDO);
    }

    private RuleDO selectByIdFromDB(String id) {
        // todo
        return new RuleDO();
    }

    @Override
    public void saveRule(Rule rule) {

        RuleDO ruleDO = ruleConverter.serialize(rule);

        // todo mybatis insert rule
    }

    @Override
    public RuleId nextId() {
        return new RuleId(UUID.randomUUID().toString());
    }
}
