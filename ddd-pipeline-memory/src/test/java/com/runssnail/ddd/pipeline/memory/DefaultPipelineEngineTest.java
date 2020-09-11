package com.runssnail.ddd.pipeline.memory;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.runssnail.ddd.pipeline.api.DefaultExchange;
import com.runssnail.ddd.pipeline.api.DefaultExecutorFactory;
import com.runssnail.ddd.pipeline.api.DefaultPhaseFactory;
import com.runssnail.ddd.pipeline.api.DefaultPipelineEngine;
import com.runssnail.ddd.pipeline.api.DefaultPipelineFactory;
import com.runssnail.ddd.pipeline.api.ExecutorFactory;
import com.runssnail.ddd.pipeline.api.PhaseRepository;
import com.runssnail.ddd.pipeline.api.PipelineRepository;
import com.runssnail.ddd.pipeline.api.StepFactory;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinitionRepository;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinitionRepository;
import com.runssnail.ddd.pipeline.definition.mysql.MysqlStepDefinitionRepository;

/**
 * @author zhengwei
 * Created on 2020-09-08
 */
public class DefaultPipelineEngineTest {

    @Test
    public void testPipelineEngine() {
        PipelineRepository pipelineRepository = createPipelineRepository();

        DefaultPipelineEngine pipelineEngine = new DefaultPipelineEngine();
        pipelineEngine.setPipelineRepository(pipelineRepository);
        pipelineEngine.init();

        DefaultExchange exchange = new DefaultExchange();
        exchange.setPipelineId("test123");
        Map<String, Object> body = new HashMap<>();
        body.put("name", "zw");
        exchange.setBody(body);
        pipelineEngine.execute(exchange);

        System.out.println(exchange.getErrorCode());
        System.out.println(exchange.getBody());

        pipelineEngine.close();
    }

    private PipelineRepository createPipelineRepository() {
        MemoryPipelineRepository pipelineRepository = new MemoryPipelineRepository();
        PipelineDefinitionRepository pipelineDefinitionRepository = new MockPipelineDefinitionRepository();
        PhaseRepository phaseRepository = new MemoryPhaseRepository();
        DefaultPipelineFactory pipelineFactory = new DefaultPipelineFactory();
        pipelineFactory.setPhaseRepository(phaseRepository);
        ExecutorFactory executorFactory = new DefaultExecutorFactory();
        MemoryStepRepository stepRepository = new MemoryStepRepository();

        DefaultPhaseFactory phaseFactory = new DefaultPhaseFactory();
        phaseFactory.setExecutorFactory(executorFactory);
        phaseFactory.setStepRepository(stepRepository);
        StepFactory stepFactory = new DefaultStepFactory();
        StepDefinitionRepository stepDefinitionRepository = new MysqlStepDefinitionRepository();
        stepRepository.setStepDefinitionRepository(stepDefinitionRepository);
        stepRepository.setStepFactory(stepFactory);

        stepRepository.setExecutorFactory(executorFactory);

        pipelineRepository.setPipelineDefinitionRepository(pipelineDefinitionRepository);
        pipelineRepository.setPipelineFactory(pipelineFactory);
        pipelineRepository.setPhaseFactory(phaseFactory);
        pipelineRepository.setPhaseRepository(phaseRepository);
        pipelineRepository.setStepFactory(stepFactory);
        pipelineRepository.setStepRepository(stepRepository);
        pipelineRepository.setExecutorFactory(executorFactory);

        pipelineRepository.init();
        return pipelineRepository;
    }
}
