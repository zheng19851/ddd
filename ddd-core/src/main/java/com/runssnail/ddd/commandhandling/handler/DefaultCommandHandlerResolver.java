package com.runssnail.ddd.commandhandling.handler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.Validate;

import com.runssnail.ddd.commandhandling.CommandException;
import com.runssnail.ddd.common.command.Command;

import lombok.extern.slf4j.Slf4j;

/**
 * 命令处理器解析器
 *
 * @author zhengwei
 * @date 2019/3/12 4:56 PM
 **/
@Slf4j
public class DefaultCommandHandlerResolver implements CommandHandlerResolver {

    /**
     * key = command class
     */
    private Map<Class, CommandHandler> commandHandlerMapping = new HashMap<>();

    @Override
    public CommandHandler resolve(Command command) throws CannotFindCommandHandlerException {
        Validate.notNull(command);

        CommandHandler commandHandler = commandHandlerMapping.get(command.getClass());

        if (commandHandler == null) {
            throw new CannotFindCommandHandlerException("cannot find CommandHandler, type:'" + command.getClass() + "'");
        }

        return commandHandler;
    }

    @PostConstruct
    public void init() {

    }

    private void register0(Map<Class, CommandHandler> requestHandlerMapping, CommandHandler commandHandler) {

        Class commandType = this.resolveCommandClass(commandHandler);

        if (commandType == null) {
            throw new CommandException("The CommandHandler support type is required");
        }
        if (requestHandlerMapping.containsKey(commandType)) {
            throw new CommandException("The CommandHandler duplicated, '" + commandType + "'");
        }
        requestHandlerMapping.put(commandType, commandHandler);
    }

    @Override
    public void registerCommandHandler(CommandHandler commandHandler) {
        this.register0(this.commandHandlerMapping, commandHandler);
    }

    private Class<? extends Command> resolveCommandClass(CommandHandler commandHandler) {
        Class commandType = commandHandler.supportCommand();
        return commandType;
    }

}
