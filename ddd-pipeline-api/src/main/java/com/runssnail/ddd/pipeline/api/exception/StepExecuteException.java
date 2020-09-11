package com.runssnail.ddd.pipeline.api.exception;

/**
 * @author zhengwei
 * Created on 2020-09-08
 */
public class StepExecuteException extends ExecuteException {

    private String pipelineId;
    private String phaseId;
    private String stepId;

    public StepExecuteException(String stepId, Throwable e) {
        super(e);
        this.stepId = stepId;
    }

    public StepExecuteException(String stepId, String msg) {
        super(msg);
        this.stepId = stepId;
    }

    public StepExecuteException(String pipelineId, String phaseId, String stepId, String msg) {
        super(msg);
        this.pipelineId = pipelineId;
        this.phaseId = phaseId;
        this.stepId = stepId;
    }

    public StepExecuteException(String pipelineId, String phaseId, String stepId, String msg, Throwable e) {
        super(msg, e);
        this.pipelineId = pipelineId;
        this.phaseId = phaseId;
        this.stepId = stepId;
    }

    public String getStepId() {
        return stepId;
    }
}
