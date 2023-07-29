package com.itheima.health.model.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 预约管理表
 */
@Data
@TableName("t_ordersetting")
public class Ordersetting {
    @TableId(type = IdType.AUTO)
    private Integer id;//主键
    private Date orderDate;
    private Integer number;
    private Integer reservations;
}
