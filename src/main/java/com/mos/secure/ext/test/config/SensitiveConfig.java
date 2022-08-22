package com.mos.secure.ext.test.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 脱敏配置初始化
 *
 * @author 小尘哥
 * @date 2022/08/22
 */
@Configuration
@ConditionalOnClass
public class SensitiveConfig {

    @Bean
    public SensitiveProp sensitiveProp() {
        return new SensitiveProp();
    }
}
