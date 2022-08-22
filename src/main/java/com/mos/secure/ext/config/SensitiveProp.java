package com.mos.secure.ext.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 脱敏配置属性
 *
 * @author 小尘哥
 * @date 2022/08/22
 * @version $Id: $Id
 */
@EnableConfigurationProperties(SensitiveProp.class)
@ConfigurationProperties(prefix = "sensitive")
@Data
public class SensitiveProp {

    Boolean   enable;
}
