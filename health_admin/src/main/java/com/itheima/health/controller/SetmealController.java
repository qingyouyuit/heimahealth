package com.itheima.health.controller;

import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.service.impl.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    /**
     * 功能描述: 套餐模块分页查询
     *
     * @return : com.itheima.health.common.ResultPageData
     */
    @PostMapping("/findPage")
    public ResultPageData findSetmealPage(@RequestBody PageParam pageParam) {
        return setmealService.findPage(pageParam);
    }

}
