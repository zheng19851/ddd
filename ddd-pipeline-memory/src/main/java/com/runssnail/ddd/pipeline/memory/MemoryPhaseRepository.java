package com.runssnail.ddd.pipeline.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runssnail.ddd.pipeline.api.Phase;
import com.runssnail.ddd.pipeline.api.PhaseRepository;

/**
 * @author zhengwei
 */
public class MemoryPhaseRepository implements PhaseRepository {
    private static final Logger log = LoggerFactory.getLogger(MemoryPhaseRepository.class);

    /**
     *
     */
    private ConcurrentMap<String, Phase> phases = new ConcurrentHashMap<>();

    /**
     * Default constructor
     */
    public MemoryPhaseRepository() {
    }

    /**
     * @param phaseIds
     * @return
     */
    @Override
    public List<Phase> getPhases(List<String> phaseIds) {
        List<Phase> phases = new ArrayList<>(phaseIds.size());
        for (String phaseId : phaseIds) {
            Phase phase = this.getPhase(phaseId);
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
    public void save(Phase phase) {
        log.info("save Phase {}", phase.getPhaseId());
        this.phases.put(phase.getPhaseId(), phase);
    }

    /**
     * @param phases
     */
    @Override
    public void saveAll(List<Phase> phases) {
        for (Phase phase : phases) {
            this.save(phase);
        }
    }

    /**
     * @param phaseId
     * @return
     */
    @Override
    public Phase remove(String phaseId) {
        log.info("remove Phase {}", phaseId);
        return this.phases.remove(phaseId);
    }

    /**
     * @param phaseIds
     * @return
     */
    @Override
    public List<Phase> removeAll(List<String> phaseIds) {
        log.info("removeAll Phase {}", phaseIds);
        List<Phase> removedList = new ArrayList<>(phaseIds.size());
        for (String phaseId : phaseIds) {
            Phase phase = this.phases.remove(phaseId);
            if (phase != null) {
                removedList.add(phase);
            }
        }
        return removedList;
    }

    @Override
    public void init() {

    }

    @Override
    public void close() {

    }
}