package com.itheima.health.common;

import lombok.Data;

@Data //不写getter setter方法 lombok.Data在编译期帮我们生成！
public class PageParam {
    private Integer currentPage;
    private Integer pageSize;
    private String queryString;
}
