package com.runssnail.ddd.common.exception;

import org.apache.commons.lang3.Validate;

public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int errorCode;
    private String errorMsg;

    public BaseException(int bizErrCode, String bizErrMsg, String message) {
        super(message);
        this.errorCode = bizErrCode;
        this.errorMsg = bizErrMsg;
    }

    public BaseException(ErrorCode errorCode, String message) {
        this(errorCode, message, null);
    }

    public BaseException(ErrorCode errorCode, String message, Throwable e) {
        super(message, e);
        Validate.notNull(errorCode);
        this.errorCode = errorCode.getErrorCode();
        this.errorMsg = errorCode.getErrorMsg();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable e) {
        super(message, e);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
