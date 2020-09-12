package com.runssnail.ddd.pipeline.api;

import com.runssnail.ddd.pipeline.api.terminate.DefaultTerminateStrategyFactory;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategyFactory;

/**
 * @author zhengwei
 * Created on 2020-09-12
 */
public abstract class BaseFactory implements Lifecycle {

    /**
     * 中断策略工厂
     */
    protected TerminateStrategyFactory terminateStrategyFactory;

    @Override
    public void init() {
        this.initTerminateStrategyFactory();
    }

    @Override
    public void close() {

    }

    protected void initTerminateStrategyFactory() {
        if (this.terminateStrategyFactory == null) {
            this.terminateStrategyFactory = new DefaultTerminateStrategyFactory();
        }
    }

    public TerminateStrategyFactory getTerminateStrategyFactory() {
        return terminateStrategyFactory;
    }

    public void setTerminateStrategyFactory(TerminateStrategyFactory terminateStrategyFactory) {
        this.terminateStrategyFactory = terminateStrategyFactory;
    }
}
