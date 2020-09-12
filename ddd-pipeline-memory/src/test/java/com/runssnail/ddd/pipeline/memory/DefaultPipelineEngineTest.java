package com.runssnail.ddd.pipeline.memory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.junit.Test;

import com.runssnail.ddd.pipeline.api.DefaultExchange;
import com.runssnail.ddd.pipeline.api.DefaultPhaseFactory;
import com.runssnail.ddd.pipeline.api.DefaultPipelineEngine;
import com.runssnail.ddd.pipeline.api.DefaultPipelineFactory;
import com.runssnail.ddd.pipeline.api.PhaseRepository;
import com.runssnail.ddd.pipeline.api.PipelineRepository;
import com.runssnail.ddd.pipeline.api.StepFactoryRepository;
import com.runssnail.ddd.pipeline.api.concurrent.DefaultExecutorFactory;
import com.runssnail.ddd.pipeline.api.concurrent.ExecutorFactory;
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
        ConcurrentMap<String, Object> body = new ConcurrentHashMap<>();
        body.put("name", "zw");
        exchange.setBody(body);

        exchange.init();

        pipelineEngine.execute(exchange);

        Map<String, Object> response = exchange.getBody();

        System.out.println(exchange.getErrorCode());
        System.out.println(exchange.getAttributes());
        System.out.println(response);

        assert response.containsKey("name");
        assert "zw".equalsIgnoreCase(response.get("name").toString());
        pipelineEngine.close();
    }

    private PipelineRepository createPipelineRepository() {
        MemoryPipelineRepository pipelineRepository = new MemoryPipelineRepository();
        PipelineDefinitionRepository pipelineDefinitionRepository = new MockPipelineDefinitionRepository();
        PhaseRepository phaseRepository = new MemoryPhaseRepository();
        DefaultPipelineFactory pipelineFactory = new DefaultPipelineFactory();
        pipelineFactory.setPhaseRepository(phaseRepository);
        pipelineFactory.init();

        ExecutorFactory executorFactory = new DefaultExecutorFactory();
        MemoryStepRepository stepRepository = new MemoryStepRepository();

        DefaultPhaseFactory phaseFactory = new DefaultPhaseFactory();
        phaseFactory.setExecutorFactory(executorFactory);
        phaseFactory.setStepRepository(stepRepository);

        StepFactoryRepository stepFactoryRepository = createStepFactoryRepository();

        StepDefinitionRepository stepDefinitionRepository = new MysqlStepDefinitionRepository();
        stepRepository.setStepDefinitionRepository(stepDefinitionRepository);
//        stepRepository.setStepFactory(stepFactory);
        stepRepository.setStepFactoryRepository(stepFactoryRepository);

        stepRepository.setExecutorFactory(executorFactory);

        pipelineRepository.setPipelineDefinitionRepository(pipelineDefinitionRepository);
        pipelineRepository.setPipelineFactory(pipelineFactory);
        pipelineRepository.setPhaseFactory(phaseFactory);
        pipelineRepository.setPhaseRepository(phaseRepository);
//        pipelineRepository.setStepFactory(stepFactory);
        pipelineRepository.setStepFactoryRepository(stepFactoryRepository);
        pipelineRepository.setStepRepository(stepRepository);
        pipelineRepository.setExecutorFactory(executorFactory);

        stepRepository.init();
        pipelineRepository.init();
        return pipelineRepository;
    }

    private SimpleStepFactoryRepository createStepFactoryRepository() {
        SimpleStepFactoryRepository simpleStepFactoryRepository = new SimpleStepFactoryRepository();
        simpleStepFactoryRepository.init();
        return simpleStepFactoryRepository;
    }
}
