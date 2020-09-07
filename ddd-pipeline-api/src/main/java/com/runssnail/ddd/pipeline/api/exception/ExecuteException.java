package com.runssnail.ddd.pipeline.api.exception;

/**
 * @author zhengwei
 * Created on 2020-09-08
 */
public class ExecuteException extends RuntimeException {
    public ExecuteException(Throwable e) {
        super(e);
    }

    public ExecuteException(String msg) {
        super(msg);
    }

    public ExecuteException(String msg, Throwable e) {
        super(msg, e);
    }
}
