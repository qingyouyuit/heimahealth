package com.itheima.health.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.mapper.CheckGroupDaoMapper;
import com.itheima.health.model.pojos.CheckGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CheckGroupService {
    public static final String CHECKGROUP_ID = "checkgroup_id";
    public static final String CHECKITEM_ID = "checkitem_id";
    @Autowired
    private CheckGroupDaoMapper checkGroupDao;

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

    /**
     * 功能描述:
     *
     * @param checkGroup
     * @param checkitemIds
     * @return : void
     */
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 添加并返回主键
        checkGroupDao.insert(checkGroup);
        setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
    }

    public void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds) {
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put(CHECKGROUP_ID, checkGroupId);
                map.put(CHECKITEM_ID, checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

    /**
     * 功能描述: 回显检查组
     *
     * @param id
     * @return : com.itheima.health.model.pojos.CheckGroup
     */
    public CheckGroup findById(Integer id) {
        return checkGroupDao.selectById(id);
    }

    /**
     * 功能描述: 回显检查组对应的检查项
     *
     * @param id
     * @return : java.util.List<java.lang.Integer>
     */
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     * 功能描述: 更新检查组
     *
     * @param checkGroup
     * @param checkitemIds
     * @return : void
     */
    public void updata(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 更新检查组
        checkGroupDao.updateById(checkGroup);
        // 删除检查组对应的检查项
        checkGroupDao.deleteCheckGroupAndCheckItemByCheckGroupId(checkGroup.getId());
        // 添加检查组对应的检查项
        setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
    }
}
