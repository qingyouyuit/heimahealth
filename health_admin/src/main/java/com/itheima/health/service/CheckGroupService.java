package com.itheima.health.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.mapper.CheckGroupDao;
import com.itheima.health.model.pojos.CheckGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Auth: qingyouyu
 * @Desc: 检查组的Service层
 * @Date: 2023/7/26 21:41
 */
@Service
@Slf4j
public class CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;


    /**
     * 功能描述: 检查组列表
     *
     * @param pageParam
     * @return : com.itheima.health.common.ResultPageData
     */
    public ResultPageData findPage(PageParam pageParam) {
        Integer currentPage = pageParam.getCurrentPage();
        Integer pageSize = pageParam.getPageSize();
        IPage<CheckGroup> page = new Page<>(currentPage, pageSize);

        LambdaQueryWrapper<CheckGroup> query = new LambdaQueryWrapper<>();

        IPage<CheckGroup> pageData = checkGroupDao.selectPage(page, query);
        long total = pageData.getTotal();
        List<CheckGroup> records = pageData.getRecords();

        ResultPageData resultPageData = new ResultPageData();
        resultPageData.setTotal(total);
        resultPageData.setRows(records);
        return resultPageData;
    }
}
