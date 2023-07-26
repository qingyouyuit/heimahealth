package com.itheima.health.model.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CheckItemDto implements Serializable {
    private String code;
    private String name;
    private String sex;
    private String age;
    private Float price;
    private String type;
    private String remark;
}
