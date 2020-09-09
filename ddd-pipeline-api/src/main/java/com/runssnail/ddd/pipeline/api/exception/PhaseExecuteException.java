package com.runssnail.ddd.pipeline.api.exception;

/**
 * @author zhengwei
 * Created on 2020-09-08
 */
public class PhaseExecuteException extends ExecuteException {

    private String phaseId;

    public PhaseExecuteException(String phaseId, Throwable e) {
        super(e);
        this.phaseId = phaseId;
    }

    public PhaseExecuteException(String phaseId, String msg) {
        super(msg);
        this.phaseId = phaseId;
    }

    public PhaseExecuteException(String phaseId, String msg, Throwable e) {
        super(msg, e);
        this.phaseId = phaseId;
    }

    public String getPhaseId() {
        return phaseId;
    }
}
