package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.mapper.CheckGroupMapper;
import com.itheima.health.mapper.CheckItemMapper;
import com.itheima.health.model.dtos.CheckItemDto;
import com.itheima.health.model.pojos.CheckItem;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.service.CheckItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper, CheckItem> implements CheckItemService {

    @Autowired
    private CheckItemMapper checkItemMapper;
    @Autowired
    private CheckGroupMapper checkGroupMapper;

    /**
     * 功能描述: 检查项目列表
     *
     * @param pageParam
     * @return : com.itheima.health.common.ResultPageData
     */
    @Override
    public ResultPageData findPage(PageParam pageParam) {
        Integer currentPage = pageParam.getCurrentPage();
        Integer pageSize = pageParam.getPageSize();
        IPage<CheckItem> page = new Page<>(currentPage, pageSize);

        LambdaQueryWrapper<CheckItem> query = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(pageParam.getQueryString())) {
            // 使用正则表达式判断是否为数字
            if (pageParam.getQueryString().matches("^[0-9]*$")) {
                query.like(CheckItem::getCode, pageParam.getQueryString());
            } else {
                query.likeRight(CheckItem::getName, pageParam.getQueryString());
            }
        }

        IPage<CheckItem> pageData = checkItemMapper.selectPage(page, query);
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
    @Override
    public void add(CheckItemDto checkItemDto) {
        CheckItem checkItem = new CheckItem();
        BeanUtils.copyProperties(checkItemDto, checkItem);
        log.info("checkItem:{}", checkItem);
        checkItemMapper.insert(checkItem);
    }

    /**
     * 功能描述: 根据id删除检查项
     *
     * @param id
     * @return : void
     */
    @Override
    public void delete(Integer id) {
        if (checkGroupMapper.findCheckGroupIdsByCheckItemId(id).size() > 0) {
            throw new RuntimeException(MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        checkItemMapper.deleteById(id);
    }

    /**
     * 功能描述: 根据id查询检查项，用于数据回显
     *
     * @param id
     * @return : com.itheima.health.model.pojos.CheckItem
     */
    @Override
    public CheckItem findById(Integer id) {
        return checkItemMapper.selectById(id);
    }

    /**
     * 功能描述: 修改检查项
     *
     * @param checkItem
     * @return : void
     */
    @Override
    public void updata(CheckItem checkItem) {
        checkItemMapper.updateById(checkItem);
    }

    /**
     * 功能描述: 查询所有检查项
     *
     * @return : java.util.List<com.itheima.health.model.pojos.CheckItem>
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemMapper.selectList(null);
    }

}
