package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.model.pojos.CheckGroup;

import java.util.List;

public interface CheckGroupService extends IService<CheckGroup> {

    ResultPageData findPage(PageParam pageParam);

    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void updata(CheckGroup checkGroup, Integer[] checkitemIds);

    boolean deleteById(Integer id);

    List<CheckGroup> findAll();
}
