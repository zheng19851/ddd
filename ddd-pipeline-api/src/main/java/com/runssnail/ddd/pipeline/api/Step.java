package com.runssnail.ddd.pipeline.api;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * 运行域步骤对象
 *
 * @author zhengwei
 */
public interface Step extends Lifecycle {

    /**
     * 执行步骤
     *
     * @param context 上下文
     * @throws ExecuteException
     */
    void execute(Exchange context) throws ExecuteException;

    /**
     * 唯一标识
     *
     * @return 唯一标识
     */
    String getStepId();

    /**
     * 步骤错误处理器
     *
     * @return
     */
    StepErrorHandler getStepErrorHandler();

}