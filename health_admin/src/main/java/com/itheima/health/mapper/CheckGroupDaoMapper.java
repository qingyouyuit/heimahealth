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
    void setCheckGroupAndCheckItem(Map map);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void deleteCheckGroupAndCheckItemByCheckGroupId(Integer id);
}
