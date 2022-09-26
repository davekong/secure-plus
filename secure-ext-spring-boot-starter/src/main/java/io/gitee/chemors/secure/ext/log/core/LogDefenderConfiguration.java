package io.gitee.chemors.secure.ext.log.core;

import io.gitee.chemors.secure.ext.config.SensitiveProp;
import io.gitee.chemors.secure.ext.log.defender.LogBackDefender;
import io.gitee.chemors.secure.ext.log.defender.SensitiveObjMessageDefender;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * 日志脱敏配置类
 *
 * @author 小尘哥
 * @date 2022/09/26
 */
@Configuration
@ImportAutoConfiguration(LogDefenderConfiguration.class)
public class LogDefenderConfiguration implements SmartInitializingSingleton {

    private SensitiveProp sensitiveProp;

    public LogDefenderConfiguration(SensitiveProp sensitiveProp) {
        this.sensitiveProp = sensitiveProp;
    }

    @Override
    public void afterSingletonsInstantiated() {
        // 装配
        LogBackDefender defender;
        defender = new SensitiveObjMessageDefender(sensitiveProp);
        // 赋值给logback消息转换器
        LogBackCoreConverter.setDefender(defender);
    }

}
