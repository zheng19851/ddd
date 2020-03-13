package com.runssnail.ddd.demo.domain.repository;

import com.runssnail.ddd.demo.domain.model.policyset.PolicySet;
import com.runssnail.ddd.demo.client.dto.domain.policyset.PolicySetId;

/**
 * @author zhengwei
 * @date 2019-11-05 14:29
 **/
public interface PolicySetRepository {

    void save(PolicySet policySet);

    PolicySet selectById(String id);

    void update(PolicySet policySet);

    /**
     * 存储实现id
     *
     * @return
     */
    PolicySetId nextId();

    /**
     * 禁用
     *
     * @param policySet
     */
    void deactivate(PolicySet policySet);

    void activate(PolicySet policySet);

    void remove(PolicySet policySet);
}
