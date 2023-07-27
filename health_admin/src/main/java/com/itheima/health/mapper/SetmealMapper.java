package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.model.pojos.Setmeal;

import java.util.HashMap;
import java.util.List;

public interface SetmealMapper extends BaseMapper<Setmeal> {
    /**
     * 功能描述: 添加套餐与检查组的关系
     *
     * @param hashMap
     * @return : void
     */
    void setSetmealAndCheckGroup(HashMap<String, Integer> hashMap);

    /**
     * 功能描述: 根据套餐id查询检查组id
     *
     * @param setmealId
     * @return : java.util.List<java.lang.Integer>
     */
    List<Integer> findSetmeaIdByCheckGroupId(Integer setmealId);

    /**
     * 功能描述: 根据套餐id删除套餐与检查组的关系
     *
     * @param id
     * @return : void
     */
    void deleteCheckGroupAndCheckItemByCheckGroupId(Integer id);
}
