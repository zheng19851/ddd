package com.runssnail.ddd.demo.domain.model.policyset;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeanUtils;

import com.runssnail.ddd.common.domain.Entity;
import com.runssnail.ddd.demo.client.dto.command.policyset.ActivatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.CreatePolicySetVersionCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.DeactivatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.RemovePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.command.policyset.UpdatePolicySetCommand;
import com.runssnail.ddd.demo.client.dto.domain.policy.PolicyId;
import com.runssnail.ddd.demo.domain.model.policy.Policy;
import lombok.Getter;

/**
 * @author zhengwei
 * @date 2019-11-05 14:20
 **/
@Getter
public class PolicySet extends Entity {

    private String policySetId;

    private String partnerCode;

    private String name;

    private String eventType;

    private String eventId;

    private PolicySetVersion version;

    private String description;

    private Integer status;

    private boolean deleted;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 最近操作人
     */
    private String operator;

    public PolicySet() {
    }

    public PolicySet(String policySetId, String partnerCode, String name, String eventType, String eventId, PolicySetVersion version) {
        this.policySetId = policySetId;
        this.partnerCode = partnerCode;
        this.name = name;
        this.eventType = eventType;
        this.eventId = eventId;
        this.version = version;
    }

    public PolicySet(String policySetId) {
        this.policySetId = policySetId;
    }

    public PolicySet version(PolicySetVersion version) {
        Validate.notNull(version);
        this.version = version;
        return this;
    }

    public PolicySet name(String name) {
        Validate.notBlank(name);
        this.name = name;
        return this;
    }

    public PolicySet description(String description) {
        Validate.notBlank(description);
        this.description = description;
        return this;
    }

    /**
     * 创建策略集
     *
     * @param policyId
     * @param name
     * @param description
     * @return
     */
    public Policy createPolicy(PolicyId policyId, String name, String description) {

        // todo 根据业务规则检查当前策略集的状态是否可以创建策略
        return new Policy(policyId, this.partnerCode, this.eventType, name, description);
    }

    /**
     * 删除策略集
     */
    public void remove(RemovePolicySetCommand command) {
        // todo 判断删除
        this.deleted = true;
        this.operator = command.getOperator();
    }

    /**
     * 禁用策略集
     */
    public void deactivate(DeactivatePolicySetCommand command) {
        // todo 判断能否禁用？
        this.status = 0;
        this.operator = command.getOperator();
    }

    /**
     * 启用策略集
     */
    public void activate(ActivatePolicySetCommand command) {
        // todo 判断能否启用？
        this.status = 1;
        this.operator = command.getOperator();
    }

    /**
     * 编辑/更新策略集信息
     *
     * @param command
     */
    public void update(UpdatePolicySetCommand command) {
        // todo 还可以在这里对业务规则进行验证
        this.name(command.getName()).description(command.getDescription()).version(PolicySetVersion.create(command.getVersion()));
    }


    /**
     * 创建版本
     *
     * @param policySetId
     * @param command
     * @return
     */
    public PolicySet createVersion(String policySetId, CreatePolicySetVersionCommand command) {
        PolicySet policySet = copyPolicySet();
        policySet.version(PolicySetVersion.create(command.getVersion()));
        return policySet;
    }

    private PolicySet copyPolicySet() {
        PolicySet policySet = new PolicySet();
        BeanUtils.copyProperties(this, policySet);
        return policySet;
    }
}
