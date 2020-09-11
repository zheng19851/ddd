package com.runssnail.ddd.pipeline.memory.grpc;

import org.apache.commons.lang3.Validate;

import com.runssnail.ddd.pipeline.api.BaseStep;
import com.runssnail.ddd.pipeline.api.Exchange;
import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * GrpcStep
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public class GrpcStep extends BaseStep {

    /**
     * 业务定义
     */
    private String bizDef;

    /**
     * 包名+类名
     */
    private String fullName;

    /**
     * 方法名
     */
    private String method;

    /**
     * 创建GrpcStep
     *
     * @param stepId   步骤唯一标识
     * @param bizDef   业务定义
     * @param fullName 报名/类名（这个是在proto里定义的）
     * @param method   方法名
     */
    public GrpcStep(String stepId, String bizDef, String fullName, String method) {
        super(stepId);
        Validate.notBlank(bizDef, "bizDef is required");
        Validate.notBlank(fullName, "fullName is required");
        Validate.notBlank(method, "method is required");
        this.bizDef = bizDef;
        this.fullName = fullName;
        this.method = method;
    }

    @Override
    protected void doExecute(Exchange exchange) throws ExecuteException {

        // todo 根据参数映射，从上下文里获取对应的请求参数


        // todo 调grpc服务
    }

    public String getBizDef() {
        return bizDef;
    }

    public void setBizDef(String bizDef) {
        this.bizDef = bizDef;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
