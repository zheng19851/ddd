package com.runssnail.ddd.spring.interceptor;

import org.springframework.beans.factory.annotation.Autowired;

import com.runssnail.ddd.commandhandling.interceptor.GlobalCommandInterceptor;
import com.runssnail.ddd.common.command.Command;
import com.runssnail.ddd.common.result.BaseResult;
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
public class I18nMessageConvertInterceptor implements GlobalCommandInterceptor<Command<BaseResult>, BaseResult> {

    @Autowired
    private IMessageSource messageSource;

    /**
     * 是否激活开关
     */
    private boolean enabled = true;

    @Override
    public void beforeHandle(Command command) {

    }

    @Override
    public void afterHandle(Command command, BaseResult result) {
        if (!enabled) {
            return;
        }

        String message = MessageConverts.convert(this.messageSource, result.getMessage());
        result.setMessage(message);
    }

}
