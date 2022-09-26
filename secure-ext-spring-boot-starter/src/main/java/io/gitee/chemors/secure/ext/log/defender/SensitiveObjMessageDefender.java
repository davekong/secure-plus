/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.log.defender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.hutool.json.JSONUtil;
import io.gitee.chemors.secure.ext.config.Constants;
import io.gitee.chemors.secure.ext.config.SensitiveProp;
import io.gitee.chemors.secure.ext.util.FieldSensitiveUtil;

/**
 * 对象脱敏器
 *
 * @author 小尘哥
 * @date 2022/09/23
 */
public class SensitiveObjMessageDefender implements LogBackDefender{

    private SensitiveProp sensitiveProp;

    public SensitiveObjMessageDefender(SensitiveProp sensitiveProp) {
        this.sensitiveProp = sensitiveProp;
    }

    @Override
    public void desensitize(ILoggingEvent event,StringBuilder buffer) {
        String message = event.getMessage();

        Object[] objects = event.getArgumentArray();
        if (objects == null || !sensitiveProp.getLogInfo().getEnable()){
            buffer.append(event.getFormattedMessage());
            return;
        }

        for (Object o : objects) {
            try {
                FieldSensitiveUtil.dealNode(o,sensitiveProp);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            message = message.replaceFirst(Constants.LOG_DELIM, JSONUtil.toJsonStr(o));
        }
        buffer.append(message);
    }
}
