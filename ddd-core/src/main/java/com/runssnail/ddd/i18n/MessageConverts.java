package com.runssnail.ddd.i18n;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.extern.slf4j.Slf4j;

/**
 * 国际化 message 转换工具
 *
 * @author zhengwei
 * @date 2020/3/7 5:00 下午
 **/
@Slf4j
public abstract class MessageConverts {

    public static String convert(IMessageSource messageSource, String message) {
        String newMessage = message;
        Locale locale = LanguageThreadContext.get();
        if (StringUtils.isNotBlank(message) && message.contains(".")) {
            // 可能包含多个验证失败信息
            if (message.contains(";")) {
                String[] messages = StringUtils.split(message, ";");
                List<String> convertedMessages = new ArrayList<>(messages.length);
                for (String m : messages) {
                    String convertedMessage = messageSource.getMessage(m, null, m, locale);
                    convertedMessages.add(convertedMessage);
                }
                newMessage = StringUtils.join(convertedMessages, ";");
            } else {
                newMessage = messageSource.getMessage(message, null, message, locale);
            }
        }

        log.debug("replace message end, old={}, new={}", message, newMessage);
        return newMessage;
    }
}
