/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.sensitive.demo;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 记录器控制器
 *
 * @author 小尘哥
 * @date 2022/09/23
 */
@RestController
@RequestMapping("demo/log")
@Slf4j
public class LoggerController {

    @GetMapping("enums")
    public String logConvertByEnum(String mobile,String name){
        log.info("log sensitive demo begin");

        SensitiveEntity sensitiveEntity = new SensitiveEntity();
        sensitiveEntity.setName(name);
        sensitiveEntity.setMobile(mobile);

        SensitiveEntity sensitiveEntity2 = new SensitiveEntity();
        sensitiveEntity2.setName(name + "222");
        sensitiveEntity2.setMobile(mobile + "333");
        log.info("sensitiveEntity1 is {} and sensitiveEntity2 is {}",sensitiveEntity,sensitiveEntity2);

        return mobile + "^" + name;
    }
}
