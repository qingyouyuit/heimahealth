package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.model.pojos.CheckGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Auth: qingyouyu
 * @Desc: 检查组的Mapper层
 * @Date: 2023/7/26 21:41
 */
@Mapper
public interface CheckGroupMapper extends BaseMapper<CheckGroup> {
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
     * @param checkGroupId
     * @return : java.util.List<java.lang.Integer>
     */
    List<Integer> findCheckItemIdsByCheckGroupId(Integer checkGroupId);

    /**
     * 功能描述: 根据检查组id删除其对应的检查项id
     *
     * @param checkGroupId
     * @return : void
     */
    void deleteCheckGroupAndCheckItemByCheckGroupId(Integer checkGroupId);

    /**
     * 功能描述:根据检查项id查找其所有的检查组id
     *
     * @param checkitemId
     * @return : java.util.List<java.lang.Integer>
     */
    List<Integer> findCheckGroupIdsByCheckItemId(Integer checkitemId);
}
