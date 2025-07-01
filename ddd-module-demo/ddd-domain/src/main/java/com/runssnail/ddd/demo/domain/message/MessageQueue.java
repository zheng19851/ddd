package com.runssnail.ddd.demo.domain.message;


/**
 * demo
 */
public interface MessageQueue {

    void push(String message);
}
