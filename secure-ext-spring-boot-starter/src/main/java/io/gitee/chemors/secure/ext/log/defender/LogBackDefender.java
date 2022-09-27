/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.log.defender;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * logback脱敏器
 *
 * @author 小尘哥
 */
public interface LogBackDefender {

    /**
     * 脱敏接口定义
     *
     * @param event   事件
     * @param buffer  缓冲
     */
    void desensitize(final ILoggingEvent event, final StringBuilder buffer);
}
