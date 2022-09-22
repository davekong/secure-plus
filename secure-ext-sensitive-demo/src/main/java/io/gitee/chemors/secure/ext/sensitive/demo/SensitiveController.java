/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.sensitive.demo;

import cn.hutool.json.JSONUtil;
import com.chemors.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 敏感控制器
 *
 * @author 小尘哥
 * @date 2022/08/22
 */
@RestController
@RequestMapping("sensitive")
public class SensitiveController {

    @Resource
    private SensitiveService sensitiveService;

    @GetMapping("simple")
    public String simpleData() {
        SensitiveEntity sensitiveEntity = sensitiveService.simpleData();
        return JSONUtil.toJsonStr(sensitiveEntity);
    }

    @GetMapping("list")
    public String listData() {
        List<SensitiveEntity> sensitiveEntities = sensitiveService.listData();
        return JSONUtil.toJsonStr(sensitiveEntities);
    }

    @GetMapping("page")
    public String page() {
        Page page = sensitiveService.page();
        return JSONUtil.toJsonStr(page);
    }

    @GetMapping("map")
    public String map() {
        Map map = sensitiveService.mapData();
        return JSONUtil.toJsonStr(map);
    }
}
