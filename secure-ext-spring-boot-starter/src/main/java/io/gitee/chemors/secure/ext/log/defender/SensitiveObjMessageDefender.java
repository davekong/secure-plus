/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.log.defender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.gitee.chemors.secure.ext.config.Constants;
import io.gitee.chemors.secure.ext.config.SensitiveProp;
import io.gitee.chemors.secure.ext.util.LogInfoCategoryUtil;

import java.util.Map;
import java.util.StringTokenizer;

/**
 * 对象脱敏器
 *
 * @author 小尘哥
 */
public class SensitiveObjMessageDefender implements LogBackDefender {

    private SensitiveProp sensitiveProp;

    public SensitiveObjMessageDefender(SensitiveProp sensitiveProp) {
        this.sensitiveProp = sensitiveProp;
    }

    @Override
    public void desensitize(ILoggingEvent event, StringBuilder buffer) {
        String message = event.getMessage();

        Object[] objects = event.getArgumentArray();
        if (objects == null || !sensitiveProp.getLogInfo().getEnable()) {
            buffer.append(event.getFormattedMessage());
            return;
        }
        if (event.getFormattedMessage().contains(Constants.LOG_STR_SPLIT)) {
            message = buildStrParams(event.getFormattedMessage());
        } else {
            for (Object o : objects) {
                message = message.replaceFirst(Constants.LOG_DELIM, dealNode(o, sensitiveProp));
            }
        }
        buffer.append(message);
    }

    private String buildStrParams(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
        while (stringTokenizer.hasMoreElements()) {
            String token = stringTokenizer.nextToken();
            if (!token.contains(Constants.LOG_STR_SPLIT)) {
                continue;
            }
            String[] arr = token.split(Constants.REG_LOG_STR_SPLIT);
            String sensitiveStr = LogInfoCategoryUtil.format(arr[0], arr[1], sensitiveProp);
            if (StrUtil.isBlankIfStr(sensitiveStr)) {
                continue;
            }
            str = str.replaceFirst(arr[0] + Constants.REG_LOG_STR_SPLIT + arr[1], sensitiveStr);
        }
        return str;
    }

    private String dealNode(Object o, SensitiveProp sensitiveProp) {
        if (o == null) {
            return null;
        }
        Map<String, Object> map = JSONUtil.toBean(JSONUtil.toJsonStr(o), Map.class);
        map.replaceAll((k, v) -> LogInfoCategoryUtil.format(k, v, sensitiveProp));
        return JSONUtil.toJsonStr(map);
    }

}
