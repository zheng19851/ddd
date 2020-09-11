package com.runssnail.ddd.pipeline.api;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;
import com.runssnail.ddd.pipeline.api.exception.StepExecuteException;

/**
 * DefaultStepErrorHandler
 *
 * @author zhengwei
 * Created on 2020-09-11
 */
public class DefaultStepErrorHandler implements StepErrorHandler {

    @Override
    public void onException(String phaseId, Step step, Exchange exchange, Throwable t) {
        String pipelineId = exchange.getPipelineId();
        String stepId = step.getStepId();
        String stepName = step.getClass().getCanonicalName();
        String exceptionMsg = ExceptionUtils.getRootCauseMessage(t);
        String msg = exceptionMsg + ", pipeline:" + pipelineId + ", phase:" + phaseId + ", step:" + stepId + "(" + stepName + ")";
        throw new StepExecuteException(pipelineId, phaseId, stepId, msg, t);

    }

    @Override
    public void onException(String phaseId, Step step, Exchange exchange, String msg) throws ExecuteException {
        String pipelineId = exchange.getPipelineId();
        String stepId = step.getStepId();
        String stepName = step.getClass().getCanonicalName();
        String finalMsg = msg + ", pipeline:" + pipelineId + ", phase:" + phaseId + ", step:" + stepId + "(" + stepName + ")";
        throw new StepExecuteException(pipelineId, phaseId, stepId, finalMsg);
    }
}
