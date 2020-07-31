package com.runssnail.ddd.commandhandling.handler;

import com.runssnail.ddd.commandhandling.CommandException;

/**
 * CannotFindCommandHandlerException
 *
 * @author zhengwei
 * @date 2019/3/12 4:58 PM
 **/
public class CannotFindCommandHandlerException extends CommandException {

    public CannotFindCommandHandlerException(String msg) {
        super(msg);
    }
}
