/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 脱敏配置属性
 *
 * @author 小尘哥
 * @version $Id: $Id
 */
@EnableConfigurationProperties(SensitiveProp.class)
@ConfigurationProperties(prefix = "sensitive")
@Data
public class SensitiveProp {

    private Boolean   enable = true;

    private Boolean   depth = false;

    private String   packages = null;

    private Boolean logEnable = true;

    private LogInfo logInfo;

}
