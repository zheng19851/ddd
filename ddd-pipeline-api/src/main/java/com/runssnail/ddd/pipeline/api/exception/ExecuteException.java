package com.runssnail.ddd.pipeline.api.exception;

/**
 * 执行异常
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public class ExecuteException extends RuntimeException {
    private int errorCode;
    private int errorMsg;

    public ExecuteException(Throwable e) {
        super(e);
    }

    public ExecuteException(String msg) {
        super(msg);
    }

    public ExecuteException(String msg, Throwable e) {
        super(msg, e);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(int errorMsg) {
        this.errorMsg = errorMsg;
    }
}
