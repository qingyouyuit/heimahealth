package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.model.pojos.Order;

import java.util.HashMap;
import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 功能描述: 查询套餐数量
     *
     * @return : java.util.List<java.util.HashMap<java.lang.String,java.lang.Object>>
     */
    List<HashMap<String, Object>> getSetmealReportCount();
}
