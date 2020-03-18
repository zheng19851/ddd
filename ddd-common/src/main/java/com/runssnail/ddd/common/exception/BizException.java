package com.runssnail.ddd.common.exception;

/**
 * 业务异常
 */
public class BizException extends BaseException {
    private static final long serialVersionUID = 1L;

    public BizException(ErrorCode errCode) {
        this(errCode, errCode.getErrorMsg());
    }

    public BizException(ErrorCode errCode, String message) {
        super(errCode, message);
    }

    public BizException(int bizErrCode, String bizErrMsg, String message) {
        super(bizErrCode, bizErrMsg, message);
    }

    public BizException(ErrorCode errorCode, String message, Throwable e) {
        super(errorCode, message, e);
    }

    public static BizException create(ErrorCode errCode) {
        BizException bizException = new BizException(errCode);
        return bizException;
    }

    public static BizException create(ErrorCode errCode, String message) {
        BizException bizException = new BizException(errCode, message);
        return bizException;
    }

    public static BizException create(int bizErrCode, String bizErrMsg, String message) {
        BizException bizException = new BizException(bizErrCode, bizErrMsg, message);
        return bizException;
    }

    public static BizException create(ErrorCode errorCode, String message, Throwable e) {
        BizException bizException = new BizException(errorCode, message, e);
        return bizException;
    }

    public static BizException createSystemError(String message) {
        BizException bizException = new BizException(BasicErrorCode.SYS_ERROR, message);
        return bizException;
    }

    public static BizException createSystemError(String message, Throwable e) {
        BizException bizException = new BizException(BasicErrorCode.SYS_ERROR, message, e);
        return bizException;
    }

}