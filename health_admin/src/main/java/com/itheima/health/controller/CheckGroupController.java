package com.itheima.health.controller;

import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.service.CheckGroupService;
import com.itheima.health.service.CheckItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Auth: qingyouyu
 * @Desc: 检查组的Controller层
 * @Date: 2023/7/26 21:40
 */
@RestController
@Slf4j
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Autowired
    private CheckGroupService checkGroupService;
    @Autowired
    private CheckItemService checkItemService;

    /**
     * 功能描述: 检查组列表
     *
     * @param pageParam
     * @return : com.itheima.health.common.ResultPageData
     */
    @PostMapping("/findPage")
    public ResultPageData findPage(@RequestBody PageParam pageParam) {
        ResultPageData resultPageData = checkGroupService.findPage(pageParam);
        return resultPageData;
    }


}
