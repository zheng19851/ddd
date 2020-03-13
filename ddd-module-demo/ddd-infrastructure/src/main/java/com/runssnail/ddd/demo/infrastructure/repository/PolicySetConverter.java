package com.runssnail.ddd.demo.infrastructure.repository;

import com.runssnail.ddd.converter.Converter;
import com.runssnail.ddd.demo.client.dataobject.PolicySetDO;
import com.runssnail.ddd.demo.domain.model.policyset.PolicySet;

import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-05 15:18
 **/
@Component
public class PolicySetConverter implements Converter<PolicySet, PolicySetDO> {

    @Override
    public PolicySetDO serialize(PolicySet policySet) {

        // todo 领域对象转换成数据对象
        PolicySetDO policySetDO = new PolicySetDO();
        policySetDO.setId(policySet.getPolicySetId());
        return policySetDO;
    }

    @Override
    public PolicySet deserialize(PolicySetDO policySetDO) {

        // todo 数据对象转换成领域对象
        return new PolicySet(policySetDO.getId());
    }
}
