package com.itheima.health.model.pojos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author author
 * @since 2023-07-26
 */
@Data
@TableName("t_checkgroup_checkitem")
public class CheckgroupCheckitem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "checkgroup_id", type = IdType.AUTO)
    private Integer checkgroupId;

    @TableField("checkitem_id")
    private Integer checkitemId;


}
