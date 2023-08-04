/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.sensitive.demo.controller;

import cn.hutool.json.JSONObject;
import io.gitee.chemors.secure.ext.sensitive.demo.entity.SensitiveEntity;
import io.gitee.chemors.secure.ext.sensitive.demo.entity.SensitiveSubEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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

        SensitiveSubEntity sensitiveSubEntity = new SensitiveSubEntity();
        sensitiveSubEntity.setAddress("中国 河南 郑州");
        sensitiveEntity.setSubEntities(Arrays.asList(sensitiveSubEntity));
        SensitiveEntity sensitiveEntity2 = new SensitiveEntity();
        sensitiveEntity2.setName(name + "222");
        sensitiveEntity2.setMobile(mobile + "333");
        log.info("基于实体类的脱敏---》sensitiveEntity1 is {} and sensitiveEntity2 is {}",sensitiveEntity,sensitiveEntity2);

        return mobile + "^" + name;
    }

    @GetMapping("json")
    public String logConvertByJson(String mobile,String name){

        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("name",name);
        jsonObject.putOpt("mobile",mobile);

        log.info("基于json的脱敏---》jsonObject is jsonObject {} ",jsonObject);

        return mobile + "^" + name;
    }

    @GetMapping("str")
    public String logConvertByStr(String mobile,String name){
        log.info("基于字符串的脱敏---》name is name^{} , and mobile is mobile^{}",name, mobile);
        return mobile + "^" + name;
    }
}
