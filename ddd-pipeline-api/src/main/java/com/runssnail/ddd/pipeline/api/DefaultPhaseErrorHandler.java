package com.runssnail.ddd.pipeline.api;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;
import com.runssnail.ddd.pipeline.api.exception.PhaseExecuteException;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategy;

/**
 * DefaultPhaseErrorHandler
 *
 * @author zhengwei
 * Created on 2020-09-11
 */
public class DefaultPhaseErrorHandler implements PhaseErrorHandler {

    @Override
    public void onException(Phase phase, Exchange exchange, Throwable t) throws ExecuteException {
        String pipelineId = exchange.getPipelineId();
        String phaseId = phase.getPhaseId();
        List<String> steps = phase.getSteps();
        String exceptionMsg = ExceptionUtils.getRootCauseMessage(t);
        String msg = exceptionMsg + ", pipeline:" + pipelineId + ", phase:" + phaseId + ", steps:" + steps;
//        throw new PhaseExecuteException(phaseId, msg, t);

        TerminateStrategy terminateStrategy = resolveTerminateStrategy(phase, exchange);
        terminateStrategy.onTerminate(exchange, new PhaseExecuteException(phaseId, msg, t));
    }

    private TerminateStrategy resolveTerminateStrategy(Phase phase, Exchange exchange) {
        TerminateStrategy terminateStrategy = phase.getTerminateStrategy();
        if (terminateStrategy != null) {
            return terminateStrategy;
        }
        return exchange.getTerminateStrategy();
    }
}
