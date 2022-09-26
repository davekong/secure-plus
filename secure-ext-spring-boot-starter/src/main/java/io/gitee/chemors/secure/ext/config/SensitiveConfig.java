/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.config;

import io.gitee.chemors.secure.ext.log.core.LogDefenderConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 脱敏配置初始化
 *
 * @author 小尘哥
 * @version $Id: $Id
 */
@Configuration
@ConditionalOnClass
public class SensitiveConfig {



    /**
     * <p>sensitiveProp.</p>
     *
     * @return a {@link SensitiveProp} object.
     */
    @Bean
    public SensitiveProp sensitiveProp() {
        return new SensitiveProp();
    }

    @Bean
    public LogDefenderConfiguration logDefenderConfiguration(){
        return new LogDefenderConfiguration(sensitiveProp());
    }
}
