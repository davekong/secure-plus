/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.log.core;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import io.gitee.chemors.secure.ext.log.defender.LogBackDefender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志支持核心转换器
 *
 * @author 小尘哥
 * @date 2022/09/23
 */
public class LogBackCoreConverter extends MessageConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogBackCoreConverter.class);

    private static LogBackDefender defender;

    @Override
    public String convert(ILoggingEvent event) {
        LOGGER.info(event.getMessage());
        StringBuilder sb = new StringBuilder();
        defender.desensitize(event,sb);
        return sb.toString();
    }

    static void setDefender(LogBackDefender defender) {
        LogBackCoreConverter.defender = defender;
    }
}
