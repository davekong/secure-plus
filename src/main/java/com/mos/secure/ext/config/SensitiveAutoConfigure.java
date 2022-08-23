package com.mos.secure.ext.config;

import com.mos.secure.ext.aspect.SensitiveAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 脱敏配置初始化
 *
 * @author 小尘哥
 * @version $Id: $Id
 */
@Configuration
public class SensitiveAutoConfigure {

    /**
     * <p>SensitiveAspect.</p>
     *
     * @return a {@link SensitiveAspect} object.
     */
    @Bean
    public SensitiveAspect sensitiveAspect() {
        return new SensitiveAspect();
    }
}
