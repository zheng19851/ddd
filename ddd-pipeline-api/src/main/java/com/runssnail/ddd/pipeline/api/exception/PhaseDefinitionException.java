package com.runssnail.ddd.pipeline.api.exception;

/**
 * 元数据定义错误
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public class PhaseDefinitionException extends DefinitionException {
    public PhaseDefinitionException(Throwable e) {
        super(e);
    }

    public PhaseDefinitionException(String msg) {
        super(msg);
    }

    public PhaseDefinitionException(String msg, Throwable e) {
        super(msg, e);
    }
}
