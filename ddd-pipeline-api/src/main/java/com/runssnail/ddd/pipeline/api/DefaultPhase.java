package com.runssnail.ddd.pipeline.api;

import java.util.List;

/**
 * @author zhengwei
 */
public class DefaultPhase extends BasePhase implements Phase {

    public DefaultPhase(String phaseId, List<String> steps, boolean parallel, StepRepository stepRepository) {
        super(phaseId, steps, parallel, stepRepository);
    }
}