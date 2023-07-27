package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.model.pojos.Setmeal;

import java.util.HashMap;

public interface SetmealMapper extends BaseMapper<Setmeal> {
    /**
     * 功能描述: 添加套餐与检查组的关系
     *
     * @param hashMap
     * @return : void
     */
    void setSetmealAndCheckGroup(HashMap<String, Integer> hashMap);
}
