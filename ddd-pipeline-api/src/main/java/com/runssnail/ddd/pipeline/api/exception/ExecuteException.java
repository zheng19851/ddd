package com.runssnail.ddd.pipeline.api.exception;

import com.runssnail.ddd.common.exception.BasicErrorCode;

/**
 * 执行异常
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public class ExecuteException extends RuntimeException {
    private int errorCode;
    private String errorMsg;

    public ExecuteException(Throwable e) {
        super(e);
        this.errorCode = BasicErrorCode.SYS_ERROR.getErrorCode();
        this.errorMsg = BasicErrorCode.SYS_ERROR.getErrorMsg();
    }

    public ExecuteException(String msg) {
        super(msg);
        this.errorCode = BasicErrorCode.SYS_ERROR.getErrorCode();
        this.errorMsg = BasicErrorCode.SYS_ERROR.getErrorMsg();
    }

    public ExecuteException(String msg, Throwable e) {
        super(msg, e);
        this.errorCode = BasicErrorCode.SYS_ERROR.getErrorCode();
        this.errorMsg = BasicErrorCode.SYS_ERROR.getErrorMsg();
    }

    public ExecuteException(int errorCode, String errorMsg, Throwable e) {
        super(e);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ExecuteException(int errorCode, String errorMsg, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ExecuteException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public ExecuteException(int errorCode, String errorMsg, String msg, Throwable e) {
        super(msg, e);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
