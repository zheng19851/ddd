package com.runssnail.ddd.common.query;

import com.runssnail.ddd.common.command.AbstractCommand;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;

/**
 * 查询命令
 *
 * @author zhengwei
 */
public abstract class Query<T extends Result> extends AbstractCommand<T> implements Command<T> {
    private static final long serialVersionUID = 1L;

}
