package com.runssnail.ddd.pipeline.api;

import java.util.List;

/**
 * 阶段执行对象管理器
 *
 * @author zhengwei
 */
public interface PhaseRepository extends Lifecycle {

    /**
     * 阶段执行对象
     *
     * @param phaseIds 唯一标识
     * @return
     */
    List<Phase> getPhases(List<String> phaseIds);

    /**
     * 阶段执行对象
     *
     * @param phaseId 唯一标识
     * @return
     */
    Phase getPhase(String phaseId);

    /**
     * 保存
     *
     * @param phase 阶段执行对象
     */
    void save(Phase phase);

    /**
     * 保存
     *
     * @param phases 阶段执行对象
     */
    void saveAll(List<Phase> phases);

    /**
     * 删除
     *
     * @param phaseId 唯一标识
     * @return 删除的对象
     */
    Phase remove(String phaseId);

    /**
     * 删除
     *
     * @param phaseIds 唯一标识
     * @return 删除的对象
     */
    List<Phase> removeAll(List<String> phaseIds);

}