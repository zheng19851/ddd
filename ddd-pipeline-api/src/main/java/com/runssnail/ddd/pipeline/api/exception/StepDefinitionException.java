package com.runssnail.ddd.pipeline.api.exception;

/**
 * 元数据定义错误
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public class StepDefinitionException extends DefinitionException {

    private String stepId;

    public StepDefinitionException(Throwable e) {
        super(e);
    }

    public StepDefinitionException(String msg) {
        super(msg);
    }

    public StepDefinitionException(String msg, Throwable e) {
        super(msg, e);
    }

    public StepDefinitionException(String stepId, String msg) {
        super(msg);
        this.stepId = stepId;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }
}
