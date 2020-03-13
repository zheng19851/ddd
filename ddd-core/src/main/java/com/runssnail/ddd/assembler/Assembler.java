package com.runssnail.ddd.assembler;

/**
 * 组装器
 *
 * @author zhengwei
 * @date 2019-11-05 18:07
 **/
public interface Assembler<Source, Target> {

    /**
     * 组装
     *
     * @param source
     * @return
     */
    Target assemble(Source source);

}
