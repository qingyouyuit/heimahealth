package com.itheima.health.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.health.mapper.CheckItemDao;
import com.itheima.health.model.pojos.CheckItem;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    public ResultPageData findPage(PageParam pageParam){
        Integer currentPage = pageParam.getCurrentPage();
        Integer pageSize = pageParam.getPageSize();
        IPage<CheckItem> page = new Page<>(currentPage,pageSize);

        LambdaQueryWrapper<CheckItem> query = new LambdaQueryWrapper<>();

        IPage<CheckItem> pageData = checkItemDao.selectPage(page, query);
        long total = pageData.getTotal();
        List<CheckItem> records = pageData.getRecords();

        ResultPageData resultPageData = new ResultPageData();
        resultPageData.setTotal(total);
        resultPageData.setRows(records);
        return resultPageData;
    }
}
