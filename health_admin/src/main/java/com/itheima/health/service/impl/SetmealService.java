package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.mapper.SetmealMapper;
import com.itheima.health.model.pojos.CheckGroup;
import com.itheima.health.model.pojos.Setmeal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
}
