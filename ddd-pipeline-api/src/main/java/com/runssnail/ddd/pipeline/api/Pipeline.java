package com.runssnail.ddd.pipeline.api;

import com.runssnail.ddd.pipeline.api.exception.ExecuteException;

/**
 * 运行域流程执行对象
 *
 * @author zhengwei
 */
public interface Pipeline extends Lifecycle {

    /**
     * 执行流程
     *
     * @param exchange 上下文
     * @throws ExecuteException
     */
    void execute(Exchange exchange) throws ExecuteException;

    /**
     * 唯一标识
     *
     * @return 唯一标识
     */
    String getPipelineId();

}