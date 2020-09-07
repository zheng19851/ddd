package com.runssnail.ddd.pipeline.api;

import java.util.List;

/**
 * 阶段执行对象管理器
 *
 * @author zhengwei
 */
public interface PhaseManager {

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
     * 更新
     *
     * @param phase 阶段执行对象
     */
    void update(Phase phase);

    /**
     * 添加
     *
     * @param phase 阶段执行对象
     */
    void add(Phase phase);

    /**
     * 添加
     *
     * @param phases 阶段执行对象
     */
    void addAll(List<Phase> phases);

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