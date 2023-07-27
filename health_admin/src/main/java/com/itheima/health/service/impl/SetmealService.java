package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.mapper.SetmealMapper;
import com.itheima.health.model.pojos.Setmeal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 功能描述: 分页查询
     *
     * @param pageParam
     * @return : com.itheima.health.common.ResultPageData
     */
    public ResultPageData findPage(PageParam pageParam) {
        Integer pageSize = pageParam.getPageSize();
        Integer currentPage = pageParam.getCurrentPage();

        Page<Setmeal> setmealPage = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();

        if (!StringUtils.isEmpty(pageParam.getQueryString())) {
            if (pageParam.getQueryString().matches("^[0-9]*$")) {
                queryWrapper.like(Setmeal::getCode, pageParam.getQueryString());
            } else if (pageParam.getQueryString().matches("^[A-Z]*$")) {
                queryWrapper.like(Setmeal::getHelpCode, pageParam.getQueryString());
            } else {
                queryWrapper.like(Setmeal::getName, pageParam.getQueryString());
            }
        }
        Page page = setmealMapper.selectPage(setmealPage, queryWrapper);

        ResultPageData resultPageData = new ResultPageData();
        resultPageData.setRows(page.getRecords());
        resultPageData.setTotal(page.getTotal());
        return resultPageData;
    }

    /**
     * 功能描述: 添加套餐
     *
     * @param setmeal
     * @param checkgroupIds
     * @return : void
     */
    public void addSetmeal(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealMapper.insert(setmeal);
        setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
    }

    /**
     * 功能描述: 添加套餐和检查组的关系
     *
     * @param id
     * @param checkgroupIds
     * @return : void
     */
    private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            for (Integer checkgroupId : checkgroupIds) {
                HashMap<String, Integer> hashMap = new HashMap<>();
                hashMap.put("setmeal_id", id);
                hashMap.put("checkgroup_id", checkgroupId);
                setmealMapper.setSetmealAndCheckGroup(hashMap);
            }
        }
    }

    /**
     * 功能描述: 查询所有套餐
     *
     * @return : java.util.List<com.itheima.health.model.pojos.Setmeal>
     */
    public List<Setmeal> findAll() {
        return setmealMapper.selectList(null);
    }


    /**
     * 功能描述: 根据id查询套餐
     *
     * @param id
     * @return : com.itheima.health.model.pojos.Setmeal
     */
    public Setmeal findById(Integer id) {
        return setmealMapper.selectById(id);
    }
}
