package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.model.dtos.CheckItemDto;
import com.itheima.health.model.pojos.CheckItem;

import java.util.List;

public interface CheckItemService extends IService<CheckItem> {

    ResultPageData findPage(PageParam pageParam);

    void add(CheckItemDto checkItemDto);

    void delete(Integer id);

    CheckItem findById(Integer id);

    void updata(CheckItem checkItem);

    List<CheckItem> findAll();
}
