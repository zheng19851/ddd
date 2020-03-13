package com.runssnail.ddd.springboot.autoconfig;

import com.runssnail.ddd.i18n.IMessageSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * IMessageSource
 *
 * @author zhengwei
 * @date 2020/3/5 11:54 上午
 *
 * @see IMessageSource
 **/
@Configuration
@ConditionalOnMissingBean(IMessageSource.class)
@Import(MessageSourceConfiguration.class)
public class MessageSourceAutoConfiguration {


}
