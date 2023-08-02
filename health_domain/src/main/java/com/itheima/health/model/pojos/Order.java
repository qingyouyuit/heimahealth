package com.itheima.health.model.pojos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员会id
     */
    private Integer memberId;

    /**
     * 约预日期
     */
    private LocalDate orderDate;

    /**
     * 约预类型 电话预约/微信预约
     */
    private String orderType;

    /**
     * 预约状态（是否到诊）
     */
    private String orderStatus;

    /**
     * 餐套id
     */
    private Integer setmealId;


}
