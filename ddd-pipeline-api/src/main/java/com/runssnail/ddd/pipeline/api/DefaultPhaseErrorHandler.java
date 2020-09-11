package com.runssnail.ddd.pipeline.api;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;
import com.runssnail.ddd.pipeline.api.exception.PhaseExecuteException;

/**
 * DefaultPhaseErrorHandler
 *
 * @author zhengwei
 * Created on 2020-09-11
 */
public class DefaultPhaseErrorHandler implements PhaseErrorHandler {

    @Override
    public void onException(Phase phase, Exchange context, Throwable t) throws ExecuteException {
        String pipelineId = context.getPipelineId();
        String phaseId = phase.getPhaseId();
        List<String> steps = phase.getSteps();
        String exceptionMsg = ExceptionUtils.getRootCauseMessage(t);
        String msg = exceptionMsg + ", pipeline:" + pipelineId + ", phase:" + phaseId + ", steps:" + steps;
        throw new PhaseExecuteException(phaseId, msg, t);
    }
}
