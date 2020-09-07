package com.runssnail.ddd.pipeline.simple;

import java.util.List;
import java.util.concurrent.ExecutorService;

import com.runssnail.ddd.pipeline.api.Interceptor;
import com.runssnail.ddd.pipeline.api.Phase;
import com.runssnail.ddd.pipeline.api.PhaseFactory;
import com.runssnail.ddd.pipeline.api.StepManager;
import com.runssnail.ddd.pipeline.api.metadata.PhaseDefinition;

/**
 * DefaultPhaseFactory
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public class DefaultPhaseFactory implements PhaseFactory {

    private StepManager stepManager;

    @Override
    public Phase create(PhaseDefinition pd) {
        DefaultPhase phase = new DefaultPhase(pd.getPhaseId(), pd.getSteps(), pd.isParallel(), this.stepManager);
        phase.setTimeout(pd.getTimeout());
        if (pd.isParallel()) {
            ExecutorService executor = createExecutor(pd);
            phase.setExecutor(executor);
        }

        List<Interceptor> interceptors = createInterceptors(pd);
        phase.setInterceptors(interceptors);

        return phase;
    }

    private List<Interceptor> createInterceptors(PhaseDefinition pd) {
        // todo 初始化拦截器
        return null;
    }

    private ExecutorService createExecutor(PhaseDefinition pd) {
        // todo 创建线程池
        // 根据queue创建
//        return new ThreadPoolExecutor(pd.getCorePoolSize(), pd.getMaxPoolSize(), new ArrayBlockingQueue<>(pd.getQueueSize()));
        return null;
    }
}
