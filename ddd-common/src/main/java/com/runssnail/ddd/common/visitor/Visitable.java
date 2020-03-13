package com.runssnail.ddd.common.visitor;

/**
 * 被访问者
 *
 * @author zhengwei
 * @date 2020/2/19 11:55 上午
 **/
public interface Visitable {

    /**
     * 接收一个访问者
     *
     * @param visitor 访问者对象
     */
    void accept(Visitor visitor);
}
