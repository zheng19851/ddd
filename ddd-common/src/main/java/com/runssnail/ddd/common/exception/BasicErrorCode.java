package com.runssnail.ddd.common.exception;


public enum BasicErrorCode implements ErrorCode {

    /**
     * 参数异常
     */
    PARAMS_ERROR(400, "params.illegal"),

    /**
     * 系统错误
     */
    SYS_ERROR(500, "system.error"),

    /**
     * 并发冲突（数据已过期）
     */
    CONCURRENCY_CONFLICT(600, "concurrency.conflict"),

    ;

    private int errCode;
    private String errMsg;

    BasicErrorCode(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrorCode() {
        return errCode;
    }

    @Override
    public String getErrorMsg() {
        return errMsg;
    }
}
