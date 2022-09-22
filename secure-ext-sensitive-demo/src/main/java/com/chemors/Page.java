/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package com.chemors;


import lombok.Data;

import java.util.List;

@Data
public class Page {

    private int current;

    private int size;

    private List records;
}
