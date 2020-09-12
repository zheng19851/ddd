package com.runssnail.ddd.pipeline.api.constant;

/**
 * 常量
 *
 * @author zhengwei
 * Created on 2020-09-10
 */
public abstract class Constants {

    /**
     * 单位毫秒
     */
    public static final long DEFAULT_SCHEDULED_PERIOD = 10000L;
    public static final int DEFAULT_SCHEDULED_CORE_POOL_SIZE = 1;

    public static final int DEFAULT_CORE_POOL_SIZE = (Runtime.getRuntime().availableProcessors() * 2) + 1;
    public static final int DEFAULT_MAX_POOL_SIZE = DEFAULT_CORE_POOL_SIZE;

    /**
     * 属性名
     */
    public static final String ATTRIBUTE_TERMINATE_STRATEGY = "terminate.strategy";

    /**
     * 单位秒
     */
    public static final long DEFAULT_KEEP_ALIVE_TIME = 60;
}
