package com.runssnail.ddd.demo.bootstrap;


import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.common.result.SingleResult;
import com.runssnail.ddd.demo.application.command.handler.policyset.CreatePolicySetCommandHandler;
import com.runssnail.ddd.demo.application.command.interceptor.policyset.CreatePolicySetInterceptor;
import com.runssnail.ddd.demo.application.command.interceptor.policyset.Order2CreatePolicySetInterceptor;
import com.runssnail.ddd.demo.application.command.validator.CreatePolicySetCommandValidator;
import com.runssnail.ddd.demo.client.api.PolicySetService;
import com.runssnail.ddd.demo.client.dto.command.policyset.ActivatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetVersionCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.DeactivatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.RemovePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.result.policyset.ActivatePolicySetResult;
import com.runssnail.ddd.demo.client.dto.result.policyset.CreatePolicySetVersionResult;
import com.runssnail.ddd.demo.client.dto.result.policyset.DeactivatePolicySetResult;
import com.runssnail.ddd.demo.client.dto.result.policyset.RemovePolicySetResult;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author zhengwei
 * @date 2019-11-05 17:29
 * @see CreatePolicySetCommandValidator
 * @see CreatePolicySetInterceptor
 * @see Order2CreatePolicySetInterceptor
 * @see CreatePolicySetCommandHandler
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.runssnail.ddd.demo.bootstrap")
public class PolicySetApplicationServiceTest {

    @Autowired
    private PolicySetService policySetService;

    @Test
    public void testCreateVersionOk() {
        CreatePolicySetVersionCommand command = new CreatePolicySetVersionCommand();
        command.setOperator("test");
        command.setPolicySetId("3333333333");
        command.setVersion("v1.0");
        CreatePolicySetVersionResult result = policySetService.createPolicySetVersion(command);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getPolicySetId());
//        System.out.println(result.getCode());
//        System.out.println(result.getPolicySetId());
    }


    @Test
    public void testDeactivatePolicySetOk() {
        DeactivatePolicySetCommand command = new DeactivatePolicySetCommand();
        command.setOperator("test");
        command.setPolicySetId("3333333333");
        DeactivatePolicySetResult result = policySetService.deactivatePolicySet(command);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getPolicySetId());
//        System.out.println(result.getCode());
//        System.out.println(result.getPolicySetId());
    }

    @Test
    public void testActivatePolicySetOk() {
        ActivatePolicySetCommand command = new ActivatePolicySetCommand();
        command.setOperator("test");
        command.setPolicySetId("3333333333");
        ActivatePolicySetResult result = policySetService.activatePolicySet(command);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getPolicySetId());
//        System.out.println(result.getCode());
//        System.out.println(result.getPolicySetId());
    }

    @Test
    public void testRemovePolicySetOk() {
        RemovePolicySetCommand command = new RemovePolicySetCommand();
        command.setOperator("test");
        command.setPolicySetId("3333333333");
        RemovePolicySetResult result = policySetService.removePolicySet(command);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getPolicySetId());
//        System.out.println(result.getCode());
//        System.out.println(result.getPolicySetId());
    }

    @Test
    public void testCreatePolicySetOk() {
        CreatePolicySetCommand createPolicySetCommand = new CreatePolicySetCommand();
        createPolicySetCommand.setPartnerCode("demo");
        createPolicySetCommand.setDescription("demo");
        createPolicySetCommand.setEventId("eventId");
        createPolicySetCommand.setEventType("Login");
        createPolicySetCommand.setName("test");
        createPolicySetCommand.setVersion("lastest");
        SingleResult<String> result = policySetService.createPolicySet(createPolicySetCommand);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getData());
//        System.out.println(result.getCode());
//        System.out.println(result.getPolicySetId());
    }

    @Test
    public void testCreatePolicySetNameOverLength() {
        CreatePolicySetCommand createPolicySetCommand = new CreatePolicySetCommand();
        createPolicySetCommand.setPartnerCode("demo");
        createPolicySetCommand.setDescription("demo");
        createPolicySetCommand.setEventId("eventId");
        createPolicySetCommand.setEventType("Login");
        createPolicySetCommand.setName("testfffffffffff");
        createPolicySetCommand.setVersion("lastest");
        SingleResult<String> result = policySetService.createPolicySet(createPolicySetCommand);

        Assert.assertNotNull(result);
        Assert.assertTrue(Result.PARAM_ERROR_CODE == result.getCode());
//        System.out.println(result.getCode());
    }
}
