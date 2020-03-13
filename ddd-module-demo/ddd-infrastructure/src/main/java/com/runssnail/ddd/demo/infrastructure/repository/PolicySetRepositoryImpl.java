package com.runssnail.ddd.demo.infrastructure.repository;

import com.runssnail.ddd.demo.client.dataobject.PolicySetDO;
import com.runssnail.ddd.demo.client.dto.domain.policyset.PolicySetId;
import com.runssnail.ddd.demo.domain.model.policyset.PolicySet;
import com.runssnail.ddd.demo.domain.repository.PolicySetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author zhengwei
 * @date 2019-11-05 15:14
 **/
@Repository
public class PolicySetRepositoryImpl implements PolicySetRepository {

    @Autowired
    private PolicySetConverter policySetConverter;

    @Override
    public void save(PolicySet policySet) {

        PolicySetDO policySetDO = policySetConverter.serialize(policySet);

        // todo 调用mybatis mapper保存数据对象
    }

    @Override
    public PolicySet selectById(String id) {

        // todo 调用mybatis mapper查询数据对象
        return new PolicySet(id);
    }

    @Override
    public void update(PolicySet policySet) {

        PolicySetDO policySetDO = policySetConverter.serialize(policySet);

        // todo 调用mybatis mapper修改数据对象
    }

    @Override
    public PolicySetId nextId() {
        return PolicySetId.create(UUID.randomUUID().toString());
    }

    @Override
    public void deactivate(PolicySet policySet) {
        // todo 调用mybatis mapper修改数据对象
    }

    @Override
    public void activate(PolicySet policySet) {

    }

    @Override
    public void remove(PolicySet policySet) {

    }
}
