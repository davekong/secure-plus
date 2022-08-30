package com.chemors;


import lombok.Data;

import java.util.List;

@Data
public class Page {

    private int current;

    private int size;

    private List records;
}
