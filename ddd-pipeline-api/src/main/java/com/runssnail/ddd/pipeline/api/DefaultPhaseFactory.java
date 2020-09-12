package com.runssnail.ddd.pipeline.api;

import java.util.List;
import java.util.concurrent.ExecutorService;

import org.apache.commons.lang3.StringUtils;

import com.runssnail.ddd.pipeline.api.concurrent.ExecutorFactory;
import com.runssnail.ddd.pipeline.api.constant.Constants;
import com.runssnail.ddd.pipeline.api.exception.PhaseDefinitionException;
import com.runssnail.ddd.pipeline.api.metadata.PhaseDefinition;
import com.runssnail.ddd.pipeline.api.terminate.TerminateStrategy;

/**
 * DefaultPhaseFactory
 *
 * @author zhengwei
 * Created on 2020-09-08
 */
public class DefaultPhaseFactory extends BaseFactory implements PhaseFactory {

    /**
     * 步骤仓储
     */
    private StepRepository stepRepository;

    /**
     * 线程池工厂
     */
    private ExecutorFactory executorFactory;


    @Override
    public Phase create(PhaseDefinition pd) throws PhaseDefinitionException {
        DefaultPhase phase = new DefaultPhase(pd.getPhaseId(), pd.getSteps(), pd.isParallel(), this.stepRepository);
        phase.setTimeout(pd.getTimeout());
        if (pd.isParallel()) {
            ExecutorService executor = createExecutor(pd);
            phase.setExecutor(executor);
        }

        // 中断策略
        String strategy = pd.getAttribute(Constants.ATTRIBUTE_TERMINATE_STRATEGY);
        if (StringUtils.isNotBlank(strategy)) {
            TerminateStrategy terminateStrategy = terminateStrategyFactory.create(strategy);
            phase.setTerminateStrategy(terminateStrategy);
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
        int corePoolSize = pd.getCorePoolSize() > 0 ? pd.getCorePoolSize() : Constants.DEFAULT_CORE_POOL_SIZE;
        int maxPoolSize = pd.getMaxPoolSize() > 0 ? pd.getMaxPoolSize() : Constants.DEFAULT_MAX_POOL_SIZE;
        return executorFactory.create(corePoolSize, maxPoolSize, pd.getQueue(), pd.getQueueSize(), "Phase");
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
