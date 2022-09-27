/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.log.core;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import io.gitee.chemors.secure.ext.log.defender.LogBackDefender;

/**
 * 日志支持核心转换器
 *
 * @author 小尘哥
 */
public class LogBackCoreConverter extends MessageConverter {

    private static LogBackDefender defender;

    @Override
    public String convert(ILoggingEvent event) {
        StringBuilder sb = new StringBuilder();
        defender.desensitize(event,sb);
        return sb.toString();
    }

    static void setDefender(LogBackDefender defender) {
        LogBackCoreConverter.defender = defender;
    }
}
