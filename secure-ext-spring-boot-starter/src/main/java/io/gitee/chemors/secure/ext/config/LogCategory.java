/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.config;

import lombok.Data;

@Data
public class LogCategory {

    private String keywords;

    private int preLength;

    private int sufLength;
}
