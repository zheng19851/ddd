package com.runssnail.ddd.common.exception;

/**
 * 并发冲突
 *
 * @author zhengwei
 * @date 2020/2/12 7:15 下午
 **/
public class ConcurrencyConflictException extends BaseException {

    public ConcurrencyConflictException(String msg) {
        super(BasicErrorCode.CONCURRENCY_CONFLICT, msg);
    }

    public ConcurrencyConflictException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
