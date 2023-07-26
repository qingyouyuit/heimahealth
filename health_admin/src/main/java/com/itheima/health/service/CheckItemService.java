package com.itheima.health.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.health.mapper.CheckItemDaoMapper;
import com.itheima.health.model.dtos.CheckItemDto;
import com.itheima.health.model.pojos.CheckItem;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CheckItemService {

    @Autowired
    private CheckItemDaoMapper checkItemDao;

    /**
     * 功能描述: 检查项目列表
     *
     * @param pageParam
     * @return : com.itheima.health.common.ResultPageData
     */
    public ResultPageData findPage(PageParam pageParam) {
        Integer currentPage = pageParam.getCurrentPage();
        Integer pageSize = pageParam.getPageSize();
        IPage<CheckItem> page = new Page<>(currentPage, pageSize);

        LambdaQueryWrapper<CheckItem> query = new LambdaQueryWrapper<>();

        IPage<CheckItem> pageData = checkItemDao.selectPage(page, query);
        long total = pageData.getTotal();
        List<CheckItem> records = pageData.getRecords();

        ResultPageData resultPageData = new ResultPageData();
        resultPageData.setTotal(total);
        resultPageData.setRows(records);
        return resultPageData;
    }

    /**
     * 功能描述: 新增检查项
     *
     * @param checkItemDto
     * @return : void
     */
    public void add(CheckItemDto checkItemDto) {
        CheckItem checkItem = new CheckItem();
        BeanUtils.copyProperties(checkItemDto, checkItem);
        log.info("checkItem:{}", checkItem);
        checkItemDao.insert(checkItem);
    }

    /**
     * 功能描述: 根据id删除检查项
     *
     * @param id
     * @return : void
     */
    public void delete(Integer id) {
        checkItemDao.deleteById(id);
    }

    /**
     * 功能描述: 根据id查询检查项，用于数据回显
     *
     * @param id
     * @return : com.itheima.health.model.pojos.CheckItem
     */
    public CheckItem findById(Integer id) {
        return checkItemDao.selectById(id);
    }

    /**
     * 功能描述: 修改检查项
     *
     * @param checkItem
     * @return : void
     */
    public void updata(CheckItem checkItem) {
        checkItemDao.updateById(checkItem);
    }

    /**
     * 功能描述: 查询所有检查项
     *
     * @return : java.util.List<com.itheima.health.model.pojos.CheckItem>
     */
    public List<CheckItem> findAll() {
        return checkItemDao.selectList(null);
    }

}
