package com.runssnail.ddd.pipeline.api;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * 步骤异常统一处理器
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public interface StepErrorHandler {

    /**
     * 处理异常
     *
     * @param phaseId 阶段唯一标识
     * @param step    步骤执行对象
     * @param context 上下文
     * @param t       异常
     * @throws ExecuteException
     */
    void onException(String phaseId, Step step, Exchange context, Throwable t) throws ExecuteException;

    /**
     * 处理异常
     *
     * @param phaseId 阶段唯一标识
     * @param step    步骤执行对象
     * @param context 上下文
     * @param msg     错误信息
     * @throws ExecuteException
     */
    void onException(String phaseId, Step step, Exchange context, String msg) throws ExecuteException;
}
