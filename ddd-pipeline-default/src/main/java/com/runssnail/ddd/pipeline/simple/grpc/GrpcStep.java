package com.runssnail.ddd.pipeline.simple.grpc;

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
     * 报名+类名
     */
    private String fullName;

    /**
     * 方法名
     */
    private String method;

    public GrpcStep(String stepId) {
        super(stepId);
    }

    @Override
    protected void doExecute(Exchange exchange) throws ExecuteException {
        // todo 调grpc服务
    }
}
