package com.itheima.health.model.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_setmeal")
public class Setmeal implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;//主键
    @TableField("code")
    private String code;//项目编码
    private String name;//项目名称
    private String sex;//适用性别
    private String age;//适用年龄（范围），例如：20-50
    private Float price;//价格
    private String remark;//项目说明
    private String attention;//注意事项
    private String helpCode;//助记码
    private String img;//图片名称
    //... 省略getter setter方法
    @TableField(exist = false)
    private List<CheckGroup> checkGroups;
}