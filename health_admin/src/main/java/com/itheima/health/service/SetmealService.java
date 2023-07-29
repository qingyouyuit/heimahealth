package com.itheima.health.service;

import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.model.pojos.Setmeal;

import java.util.List;

public interface SetmealService {
    ResultPageData findPage(PageParam pageParam);

    void addSetmeal(Setmeal setmeal, Integer[] checkgroupIds);

    List<Setmeal> findAll();

    Setmeal findById(Integer id);

    List<Integer> findCheckGroupIdBysetmealId(Integer setmealId);

    void updata(Setmeal setmeal, Integer[] checkgroupIds);

    boolean deleteById(Integer id);

}
