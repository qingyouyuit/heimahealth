package com.itheima.health.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetmealDto {
    private String code;
    private String name;
    private String helpCode;
    private String sex;
    private Float price;
    private String age;
    private String remark;
    private String attention;
    private String img;
}
