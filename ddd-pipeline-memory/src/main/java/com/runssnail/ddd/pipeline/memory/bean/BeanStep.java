package com.runssnail.ddd.pipeline.memory.bean;

import com.runssnail.ddd.pipeline.api.BaseStep;
import com.runssnail.ddd.pipeline.api.Exchange;
import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * @author zhengwei
 * Created on 2020-09-11
 */
public class BeanStep extends BaseStep {

    public BeanStep(String stepId) {
        super(stepId);
    }

    @Override
    protected void doExecute(Exchange exchange) throws ExecuteException {
        System.out.println("bean test");
    }
}
