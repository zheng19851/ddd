package com.runssnail.ddd.command;

/**
 * @author zhengwei
 * @date 2019-12-20 10:32
 **/
public class CommandException extends RuntimeException {
    public CommandException(String msg) {
        super(msg);
    }
}
