package com.runssnail.ddd.demo.infrastructure.repository;

import com.runssnail.ddd.demo.client.dto.domain.policy.PolicyId;
import com.runssnail.ddd.demo.domain.model.policy.Policy;
import com.runssnail.ddd.demo.domain.repository.PolicyRepository;

import org.springframework.stereotype.Repository;

/**
 * @author zhengwei
 * @date 2019-11-08 22:34
 **/
@Repository
public class PolicyRepositoryImpl implements PolicyRepository {

    @Override
    public void save(Policy policy) {
        // todo 通过orm 保存数据
    }

    @Override
    public Policy selectById(String id) {
        return new Policy(PolicyId.create(id));
    }
}
