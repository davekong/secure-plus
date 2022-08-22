package com.mos.secure.ext;

import cn.hutool.json.JSONUtil;
import com.mos.secure.ext.demo.SensitiveEntity;
import com.mos.secure.ext.demo.SensitiveService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
class SensitiveSpringBootStarterApplicationTests {

    @Resource
    private SensitiveService sensitiveService;

    @Test
    void contextLoads() {
        SensitiveEntity sensitiveEntity = sensitiveService.simpleData();
        log.info("d--->{}", JSONUtil.toJsonStr(sensitiveEntity));
    }

    @Test
    void listData() {
        List<SensitiveEntity> d = sensitiveService.listData();
        log.info("d--->{}", JSONUtil.toJsonStr(d));
    }
}
