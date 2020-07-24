package com.runssnail.ddd.spring.interceptor;

import org.springframework.beans.factory.annotation.Autowired;

import com.runssnail.ddd.commandhandling.interceptor.GlobalCommandInterceptor;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.Result;
import com.runssnail.ddd.i18n.IMessageSource;
import com.runssnail.ddd.i18n.MessageConverts;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 响应内容根据语言转换
 *
 * @author zhengwei
 */
@Slf4j
@Getter
@Setter
public class I18nMessageConvertInterceptor implements GlobalCommandInterceptor<Command<Result>, Result> {

    @Autowired
    private IMessageSource messageSource;

    private boolean enabled = true;

    @Override
    public void beforeHandle(Command command) {

    }

    @Override
    public void afterHandle(Command command, Result result) {
        if (!enabled) {
            return;
        }

        String message = MessageConverts.convert(this.messageSource, result.getMessage());
        result.setMessage(message);
    }

}
