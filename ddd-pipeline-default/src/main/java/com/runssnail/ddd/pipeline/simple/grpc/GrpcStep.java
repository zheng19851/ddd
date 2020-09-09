package com.runssnail.ddd.pipeline.simple.grpc;

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
     * 包名+类名
     */
    private String fullName;

    /**
     * 方法名
     */
    private String method;

    /**
     * 超时时间，单位毫秒
     */
    private long timeout;

    public GrpcStep(String stepId, String fullName, String method) {
        super(stepId);
        Validate.notBlank(fullName, "fullName is required");
        Validate.notBlank(method, "method is required");
        this.fullName = fullName;
        this.method = method;
    }

    @Override
    protected void doExecute(Exchange exchange) throws ExecuteException {

        // todo 根据参数映射，从上下文里获取对应的请求参数

        // todo 调grpc服务
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

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
