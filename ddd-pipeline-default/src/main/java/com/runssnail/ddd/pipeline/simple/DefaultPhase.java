package com.runssnail.ddd.pipeline.simple;

import java.util.List;

import com.runssnail.ddd.pipeline.api.BasePhase;
import com.runssnail.ddd.pipeline.api.Phase;
import com.runssnail.ddd.pipeline.api.StepManager;

/**
 * @author zhengwei
 */
public class DefaultPhase extends BasePhase implements Phase {

    public DefaultPhase(String phaseId, List<String> steps, boolean parallel, StepManager stepManager) {
        super(phaseId, steps, parallel, stepManager);
    }
}