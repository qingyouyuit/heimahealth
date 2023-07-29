package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.mapper.CheckGroupMapper;
import com.itheima.health.model.pojos.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auth: qingyouyu
 * @Desc: 检查组的Service层
 * @Date: 2023/7/26 21:41
 */
@Service
@Slf4j
public class CheckGroupServiceImpl extends ServiceImpl<CheckGroupMapper, CheckGroup> implements CheckGroupService {
    public static final String CHECKGROUP_ID = "checkgroup_id";
    public static final String CHECKITEM_ID = "checkitem_id";
    @Autowired
    private CheckGroupMapper checkGroupMapper;

    /**
     * 功能描述: 检查组列表
     *
     * @param pageParam
     * @return : com.itheima.health.common.ResultPageData
     */
    @Override
    public ResultPageData findPage(PageParam pageParam) {
        Integer currentPage = pageParam.getCurrentPage();
        Integer pageSize = pageParam.getPageSize();
        IPage<CheckGroup> page = new Page<>(currentPage, pageSize);

        LambdaQueryWrapper<CheckGroup> query = new LambdaQueryWrapper<>();
        // 判断是否有查询条件,isNotEmpty()判断字符串是否不为空,不为空返回true
        if (!StringUtils.isEmpty(pageParam.getQueryString())) {
            // 使用正则表达式判断是否为数字
            if (pageParam.getQueryString().matches("^[0-9]*$")) {
                query.like(CheckGroup::getCode, pageParam.getQueryString());
                // 使用正则表达式判断是否为大写字母
            } else if (pageParam.getQueryString().matches("^[A-Z]*$")) {
                query.like(CheckGroup::getHelpCode, pageParam.getQueryString());
                // 其余情况模糊查询
            } else {
                query.like(CheckGroup::getName, pageParam.getQueryString());
            }
        }

        IPage<CheckGroup> pageData = checkGroupMapper.selectPage(page, query);
        long total = pageData.getTotal();
        List<CheckGroup> records = pageData.getRecords();

        ResultPageData resultPageData = new ResultPageData();
        resultPageData.setTotal(total);
        resultPageData.setRows(records);
        return resultPageData;
    }

    /**
     * 功能描述: 添加检查组
     *
     * @param checkGroup
     * @param checkitemIds
     * @return : void
     */
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 添加并返回主键
        checkGroupMapper.insert(checkGroup);
        setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
    }
    /**
     * 功能描述: 回显检查组
     *
     * @param id
     * @return : com.itheima.health.model.pojos.CheckGroup
     */
    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupMapper.selectById(id);
    }

    /**
     * 功能描述: 回显检查组对应的检查项
     *
     * @param id
     * @return : java.util.List<java.lang.Integer>
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupMapper.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     * 功能描述: 更新检查组
     *
     * @param checkGroup
     * @param checkitemIds
     * @return : void
     */
    @Override
    public void updata(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 更新检查组
        checkGroupMapper.updateById(checkGroup);
        // 删除检查组对应的检查项
        checkGroupMapper.deleteCheckGroupAndCheckItemByCheckGroupId(checkGroup.getId());
        // 添加检查组对应的检查项
        setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
    }

    /**
     * 功能描述: 删除检查组
     *
     * @param id
     * @return : void
     */
    @Override
    public boolean deleteById(Integer id) {
        // 判断是否有检查项关联
        if (checkGroupMapper.findCheckItemIdsByCheckGroupId(id).size() > 0) {
            return false;
        }
        // 删除检查组
        checkGroupMapper.deleteById(id);
        return true;
    }

    /**
     * 功能描述: 返回所有数据
     *
     * @return : java.util.List<com.itheima.health.model.pojos.CheckGroup>
     */
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupMapper.selectList(null);
    }

    /**
     * 功能描述: 将检查组与检查项建立联系
     *
     * @param checkGroupId
     * @param checkitemIds
     * @return : void
     */
    private void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds) {
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put(CHECKGROUP_ID, checkGroupId);
                map.put(CHECKITEM_ID, checkitemId);
                checkGroupMapper.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
