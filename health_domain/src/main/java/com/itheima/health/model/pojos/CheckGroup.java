package com.itheima.health.model.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 检查组
 */
@TableName("t_checkgroup")
@Data
public class CheckGroup implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;//主键
    @TableField("code")
    private String code;//项目编码
    private String name;//项目名称
    private String helpCode;//助记码  help_code
    private String sex;//性别
    private String remark;//项目说明
    private String attention;//注意事项
    //... 省略getter setter方法

    @TableField(exist = false)
    private List<CheckItem> checkItems;
}