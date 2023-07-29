package com.itheima.health.model.pojos;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrdersettingData {
    @ExcelProperty("日期")
    private String orderDate;
    @ExcelProperty("可预约数量")
    private Integer number;
    @ExcelIgnore
    private Integer id;//主键
    @ExcelIgnore
    private Integer reservations;
}
