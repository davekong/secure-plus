/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.config;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 日志信息
 *
 * @author 小尘哥
 */
@Data
@NoArgsConstructor
public class LogInfo {

    private Boolean enable = true;

    private List<LogCategory> categories;

}
