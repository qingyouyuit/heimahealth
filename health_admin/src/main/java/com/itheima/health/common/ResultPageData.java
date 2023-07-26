package com.itheima.health.common;

import lombok.Data;

import java.util.List;

@Data
public class ResultPageData {

    private long total;
    private List rows;
}
