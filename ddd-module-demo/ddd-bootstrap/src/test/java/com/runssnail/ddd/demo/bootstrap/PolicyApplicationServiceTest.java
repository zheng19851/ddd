package com.runssnail.ddd.demo.bootstrap;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.demo.application.command.handler.policy.CreatePolicyCommandHandler;
import com.runssnail.ddd.demo.application.command.validator.CreatePolicyCommandValidator;
import com.runssnail.ddd.demo.client.api.PolicyService;
import com.runssnail.ddd.demo.client.dto.command.policy.CreatePolicyCommand;
import com.runssnail.ddd.demo.client.dto.result.policy.CreatePolicyResult;


/**
 *
 * @see CreatePolicyCommandHandler
 * @see CreatePolicyCommandValidator
 *
 * @author zhengwei
 * @date 2019-11-05 17:29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.runssnail.ddd.demo.bootstrap")
public class PolicyApplicationServiceTest {

    @Autowired
    private PolicyService policyService;

    @Test
    public void testCreatePolicyOk() {
        CreatePolicyCommand command = new CreatePolicyCommand("policySetId", "demo", "test-create-policy");
        command.setDescription("demo");
        CreatePolicyResult result = policyService.createPolicy(command);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getCode());
        Assert.assertTrue(result.isSuccess());
    }


    @Test
    public void testCreatePolicyNameOverLength() {
        CreatePolicyCommand command = new CreatePolicyCommand("policySetId", "demo", "test-create-policy-339328202003309393938928938938928932899823");
        command.setDescription("demo");
        CreatePolicyResult result = policyService.createPolicy(command);

        Assert.assertNotNull(result);
        Assert.assertTrue(!result.isSuccess());
        Assert.assertTrue(Result.PARAM_ERROR_CODE == result.getCode());
//        System.out.println(result.getMsg());
    }
}
