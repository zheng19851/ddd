package com.runssnail.ddd.spring;

import com.runssnail.ddd.i18n.IMessageSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengwei
 */
@Slf4j
public class SpringMessageSource implements IMessageSource {

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean contains(String code, Locale locale) {
        String message;
        try {
            message = messageSource.getMessage(code, null, locale);
        } catch (NoSuchMessageException e) {
            log.debug("No message found under code '{}'", code, e);
            return false;
        }

        return StringUtils.isNotBlank(message) ? true : false;
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(code, args, locale);
    }
}
