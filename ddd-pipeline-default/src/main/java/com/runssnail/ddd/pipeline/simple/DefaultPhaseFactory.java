package com.runssnail.ddd.pipeline.simple;

import java.util.List;
import java.util.concurrent.ExecutorService;

import com.runssnail.ddd.pipeline.api.ExecutorFactory;
import com.runssnail.ddd.pipeline.api.Interceptor;
import com.runssnail.ddd.pipeline.api.Phase;
import com.runssnail.ddd.pipeline.api.PhaseFactory;
import com.runssnail.ddd.pipeline.api.StepRepository;
import com.runssnail.ddd.pipeline.api.exception.PhaseDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.PhaseDefinition;

/**
 * DefaultPhaseFactory
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public class DefaultPhaseFactory implements PhaseFactory {

    private StepRepository stepRepository;

    private ExecutorFactory executorFactory;

    @Override
    public Phase create(PhaseDefinition pd) throws PhaseDefinitionException {
        DefaultPhase phase = new DefaultPhase(pd.getPhaseId(), pd.getSteps(), pd.isParallel(), this.stepRepository);
        if (pd.isParallel()) {
            phase.setTimeout(pd.getTimeout());
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
        return executorFactory.create(pd.getCorePoolSize(), pd.getMaxPoolSize(), pd.getQueue(), pd.getQueueSize(), "Phase");
    }

    public StepRepository getStepRepository() {
        return stepRepository;
    }

    public void setStepRepository(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    public ExecutorFactory getExecutorFactory() {
        return executorFactory;
    }

    public void setExecutorFactory(ExecutorFactory executorFactory) {
        this.executorFactory = executorFactory;
    }
}
