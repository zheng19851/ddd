package com.runssnail.ddd.demo.application.assembler.policyset;

import com.runssnail.ddd.assembler.Assembler;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetCommand;
import com.runssnail.ddd.demo.domain.model.policyset.PolicySet;
import com.runssnail.ddd.demo.domain.service.PolicySetDomainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-05 18:10
 **/
@Component
public class CreatePolicySetAssembler implements Assembler<CreatePolicySetCommand, PolicySet> {

    @Autowired
    private PolicySetDomainService policySetDomainService;

    @Override
    public PolicySet assemble(CreatePolicySetCommand command) {
        PolicySet policySet = policySetDomainService.createPolicySet(command);
        return policySet;
    }
}
