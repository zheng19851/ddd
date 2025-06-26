package com.runssnail.ddd.common.exception;

/**
 * 并发冲突
 *
 * @author zhengwei
 * @date 2020/2/12 7:15 下午
 **/
public class ConcurrencyConflictException extends BaseException {

    public ConcurrencyConflictException() {
        super(BasicErrorCode.CONCURRENCY_CONFLICT, BasicErrorCode.CONCURRENCY_CONFLICT.getErrorMsg());
    }

    public ConcurrencyConflictException(String msg) {
        super(BasicErrorCode.CONCURRENCY_CONFLICT, msg);
    }

    public ConcurrencyConflictException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * 用来检查更新db后的记录数，如果count != 1，那么throw ConcurrencyConflictException
     *
     * @param count 变更的记录数
     * @param msg   信息
     * @see ConcurrencyConflicts
     */
    @Deprecated
    public static void check(int count, String msg) throws ConcurrencyConflictException {
        if (count != 1) {
            throw new ConcurrencyConflictException(msg);
        }
    }

    /**
     * 用来检查更新db后的记录数，如果count != 1，那么throw ConcurrencyConflictException
     *
     * @param count     变更的记录数
     * @param errorCode 错误码
     * @param msg       信息
     * @throws ConcurrencyConflictException
     * @see ConcurrencyConflicts
     */
    @Deprecated
    public static void check(int count, ErrorCode errorCode, String msg) throws ConcurrencyConflictException {
        if (count != 1) {
            throw new ConcurrencyConflictException(errorCode, msg);
        }
    }
}
