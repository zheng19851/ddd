package com.runssnail.ddd.pipeline.api;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;
import com.runssnail.ddd.pipeline.api.exception.StepExecuteException;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategy;

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
//        throw new StepExecuteException(pipelineId, phaseId, stepId, msg, t);
        TerminateStrategy terminateStrategy = resolveTerminateStrategy(step, exchange);
        terminateStrategy.onTerminate(exchange, new StepExecuteException(pipelineId, phaseId, stepId, msg, t));
    }

    @Override
    public void onException(String phaseId, Step step, Exchange exchange, String msg) throws ExecuteException {
        String pipelineId = exchange.getPipelineId();
        String stepId = step.getStepId();
        String stepName = step.getClass().getCanonicalName();
        String finalMsg = msg + ", pipeline:" + pipelineId + ", phase:" + phaseId + ", step:" + stepId + "(" + stepName + ")";
//        throw new StepExecuteException(pipelineId, phaseId, stepId, finalMsg);
        TerminateStrategy terminateStrategy = resolveTerminateStrategy(step, exchange);
        terminateStrategy.onTerminate(exchange, new StepExecuteException(pipelineId, phaseId, stepId, finalMsg));
    }

    private TerminateStrategy resolveTerminateStrategy(Step step, Exchange exchange) {
        TerminateStrategy terminateStrategy = step.getTerminateStrategy();
        if (terminateStrategy != null) {
            return terminateStrategy;
        }
        return exchange.getTerminateStrategy();
    }
}
