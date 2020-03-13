package com.runssnail.ddd.common.visitor;

/**
 * 访问者
 *
 * @author zhengwei
 * @date 2020/2/19 11:55 上午
 **/
public interface Visitor<T> {

    /**
     * 访问
     *
     * @param t 需要访问的对象
     */
    void visit(T t);
}
