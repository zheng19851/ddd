package com.runssnail.ddd.demo.domain.repository;

import com.runssnail.ddd.demo.domain.model.policy.Policy;

/**
 * @author zhengwei
 * @date 2019-11-05 14:29
 **/
public interface PolicyRepository {

    void save(Policy policy);

    Policy selectById(String id);

}
