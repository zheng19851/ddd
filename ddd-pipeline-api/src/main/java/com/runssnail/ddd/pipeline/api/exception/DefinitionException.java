package com.runssnail.ddd.pipeline.api.exception;

/**
 * 元数据定义错误
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public abstract class DefinitionException extends RuntimeException {
    public DefinitionException(Throwable e) {
        super(e);
    }

    public DefinitionException(String msg) {
        super(msg);
    }

    public DefinitionException(String msg, Throwable e) {
        super(msg, e);
    }
}
