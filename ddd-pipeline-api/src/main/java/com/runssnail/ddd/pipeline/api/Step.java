package com.runssnail.ddd.pipeline.api;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategy;

/**
 * 运行域步骤对象
 *
 * @author zhengwei
 */
public interface Step extends Lifecycle {

    /**
     * 唯一标识
     *
     * @return 唯一标识
     */
    String getStepId();

    /**
     * 执行步骤
     *
     * @param context 上下文
     * @throws ExecuteException
     */
    void execute(Exchange context) throws ExecuteException;

    /**
     * 中断策略
     *
     * @param terminateStrategy 中断策略
     */
    void setTerminateStrategy(TerminateStrategy terminateStrategy);

    /**
     * 中断策略
     *
     * @return 中断策略
     */
    TerminateStrategy getTerminateStrategy();

    /**
     * 步骤错误处理器
     *
     * @return
     */
    StepErrorHandler getStepErrorHandler();

}