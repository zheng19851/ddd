package com.runssnail.ddd.pipeline.api;

import java.util.List;

/**
 * 步骤执行对象管理器
 *
 * @author zhengwei
 */
public interface StepRepository extends Lifecycle {

    /**
     * 步骤执行对象
     *
     * @param stepIds 唯一标识
     * @return 步骤执行对象
     */
    List<Step> getSteps(List<String> stepIds);

    /**
     * 步骤执行对象
     *
     * @param stepId 唯一标识
     * @return 步骤执行对象
     */
    Step getStep(String stepId);

    /**
     * 保存
     *
     * @param step 步骤执行对象
     */
    void save(Step step);

    /**
     * 保存
     *
     * @param steps 步骤执行对象
     */
    void saveAll(List<Step> steps);

    /**
     * 删除
     *
     * @param stepId 唯一标识
     * @return 删除的步骤执行对象
     */
    Step remove(String stepId);

    /**
     * 删除
     *
     * @param stepIds 唯一标识
     * @return 步骤执行对象
     */
    List<Step> removeAll(List<String> stepIds);

}