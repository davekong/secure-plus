/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.sensitive.demo.base;


import lombok.Data;

import java.util.List;

/**
 * @author 小尘哥
 */
@Data
public class Page<T> {

    private int current;

    private int size;

    private List<T> records;
}
