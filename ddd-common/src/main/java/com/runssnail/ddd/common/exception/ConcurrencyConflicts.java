package com.runssnail.ddd.common.exception;

/**
 * 并发冲突异常检测工具
 *
 * @author zhengwei
 * @date 2020/3/20 10:35 上午
 **/
public class ConcurrencyConflicts {
    private ConcurrencyConflicts() {
    }

    /**
     * 用来检查更新db后的记录数，如果count != 1，那么throw ConcurrencyConflictException
     *
     * @param count 变更的记录数
     * @param msg   信息
     */
    public static void check(int count, String msg) throws ConcurrencyConflictException {
        if (count != 1) {
            throw new ConcurrencyConflictException(msg);
        }
    }

    public static void check(int count, ErrorCode errorCode, String msg) throws ConcurrencyConflictException {
        if (count != 1) {
            throw new ConcurrencyConflictException(errorCode, msg);
        }
    }

}
