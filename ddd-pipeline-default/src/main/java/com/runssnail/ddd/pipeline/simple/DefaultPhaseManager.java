package com.runssnail.ddd.pipeline.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.runssnail.ddd.pipeline.api.Phase;
import com.runssnail.ddd.pipeline.api.PhaseManager;

/**
 * @author zhengwei
 */
public class DefaultPhaseManager implements PhaseManager {

    /**
     *
     */
    private ConcurrentMap<String, Phase> phases = new ConcurrentHashMap<>();

    /**
     * Default constructor
     */
    public DefaultPhaseManager() {
    }

    /**
     * @param phaseIds
     * @return
     */
    @Override
    public List<Phase> getPhases(List<String> phaseIds) {
        List<Phase> phases = new ArrayList<>(phaseIds.size());
        for (String name : phaseIds) {
            Phase phase = this.getPhase(name);
            if (phase != null) {
                phases.add(phase);
            }
        }
        return phases;
    }

    /**
     * @param phaseId
     * @return
     */
    @Override
    public Phase getPhase(String phaseId) {
        return phases.get(phaseId);
    }

    /**
     * @param phase
     */
    @Override
    public void update(Phase phase) {
        this.phases.put(phase.getPhaseId(), phase);
    }

    /**
     * @param phase
     */
    @Override
    public void add(Phase phase) {
        this.phases.put(phase.getPhaseId(), phase);
    }

    /**
     * @param phases
     */
    @Override
    public void addAll(List<Phase> phases) {
        for (Phase phase : phases) {
            this.add(phase);
        }
    }

    /**
     * @param phaseId
     * @return
     */
    @Override
    public Phase remove(String phaseId) {
        return this.phases.remove(phaseId);
    }

    /**
     * @param phaseIds
     * @return
     */
    @Override
    public List<Phase> removeAll(List<String> phaseIds) {
        List<Phase> removedList = new ArrayList<>(phaseIds.size());
        for (String name : phaseIds) {
            Phase phase = this.phases.remove(name);
            if (phase != null) {
                removedList.add(phase);
            }
        }
        return removedList;
    }

}