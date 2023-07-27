package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.model.pojos.CheckGroup;

import java.util.List;
import java.util.Map;

/**
 * @Auth: qingyouyu
 * @Desc: 检查组的Mapper层
 * @Date: 2023/7/26 21:41
 */
public interface CheckGroupDaoMapper extends BaseMapper<CheckGroup> {
    /**
     * 功能描述: 将检查组id与检查项id添加进中间表
     *
     * @param map
     * @return : void
     */
    void setCheckGroupAndCheckItem(Map map);

    /**
     * 功能描述:根据检查组id查找其所有的检查项id
     *
     * @param id
     * @return : java.util.List<java.lang.Integer>
     */
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    /**
     * 功能描述: 根据检查组id删除其对应的检查项id
     *
     * @param id
     * @return : void
     */
    void deleteCheckGroupAndCheckItemByCheckGroupId(Integer id);
}
