package com.runssnail.ddd.pipeline.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.runssnail.ddd.pipeline.api.metadata.BasePipelineDefinitionRepository;
import com.runssnail.ddd.pipeline.api.metadata.DefaultPhaseDefinition;
import com.runssnail.ddd.pipeline.api.metadata.DefaultPipelineDefinition;
import com.runssnail.ddd.pipeline.api.metadata.DefaultStepDefinition;
import com.runssnail.ddd.pipeline.api.metadata.PhaseDefinition;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinition;
import com.runssnail.ddd.pipeline.api.metadata.PipelineDefinitionRepository;
import com.runssnail.ddd.pipeline.api.metadata.StepDefinition;

/**
 * 从mysql数据库里加载Pipeline
 *
 * @author zhengwei
 */
public class MockPipelineDefinitionRepository extends BasePipelineDefinitionRepository implements PipelineDefinitionRepository {

    /**
     * Default constructor
     */
    public MockPipelineDefinitionRepository() {
    }

    /**
     * @return
     */
    @Override
    public Map<String, PipelineDefinition> getPipelineDefinitions() {
        Map<String, PipelineDefinition> map = new HashMap<>();
        map.put("test123", createPipelineDefinition());
        return map;
    }

    @Override
    public List<PipelineDefinition> getPipelineDefinitions(boolean onlyEnabled, long updateTimeStart) {

        // 如果PipelineDefinition被删除了，那么Phase就不用加载了
        PipelineDefinition pipelineDefinition = createPipelineDefinition();
        List<PipelineDefinition> result = new ArrayList<>(1);
        result.add(pipelineDefinition);
        return result;
    }

    private DefaultPipelineDefinition createPipelineDefinition() {
        DefaultPipelineDefinition pipelineDefinition = new DefaultPipelineDefinition();
        pipelineDefinition.setPipelineId("test123");


        List<PhaseDefinition> phaseDefinitions = createPhaseDefinitions();

        pipelineDefinition.setPhaseDefinitions(phaseDefinitions);

        return pipelineDefinition;
    }

    private List<PhaseDefinition> createPhaseDefinitions() {

        List<PhaseDefinition> phases = new ArrayList<>();
        PhaseDefinition phaseDefinition = createPhaseDefinition();
        phases.add(phaseDefinition);
        return phases;
    }

    private PhaseDefinition createPhaseDefinition() {
        DefaultPhaseDefinition phaseDefinition = new DefaultPhaseDefinition();
        phaseDefinition.setPhaseId("testPhase1");

        List<StepDefinition> stepDefinitions = new ArrayList<>();

        StepDefinition stepDefinition = createStepDefinition();
        stepDefinitions.add(stepDefinition);

        phaseDefinition.setStepDefinitions(stepDefinitions);
        return phaseDefinition;
    }

    private StepDefinition createStepDefinition() {
        DefaultStepDefinition stepDefinition = new DefaultStepDefinition();

        stepDefinition.setStepId("testStep1");
        stepDefinition.setStepType("bean");

        return stepDefinition;
    }

    /**
     * @param pipelineId
     * @return
     */
    @Override
    public PipelineDefinition get(String pipelineId) {
        return createPipelineDefinition();
    }

}