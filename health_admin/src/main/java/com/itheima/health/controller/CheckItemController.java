package com.itheima.health.controller;

import com.itheima.health.config.BeanContext;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckItemController {
    @Autowired
    private BeanContext beanContext;

    @Autowired
    private CheckItemService checkItemService;

    @PostMapping("/checkitem/findPage")
    public ResultPageData findPage(@RequestBody PageParam pageParam){


        System.out.println(pageParam);
        //模拟数据
//        ResultPageData resultPageData = new ResultPageData();
//        resultPageData.setTotal(45);
//        resultPageData.setRows(new ArrayList());
        ResultPageData resultPageData = checkItemService.findPage(pageParam);
        return resultPageData;
    }
}
