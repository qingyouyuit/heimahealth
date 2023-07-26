package com.itheima.health.controller;

import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.service.CheckGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auth: qingyouyu
 * @Desc: 检查组的Controller层
 * @Date: 2023/7/26 21:40
 */
@RestController
@Slf4j
public class CheckGroupController {
    @Autowired
    private CheckGroupService checkGroupService;

    @PostMapping("/checkgroup/findPage")
    public ResultPageData findPage(@RequestBody PageParam pageParam) {
        ResultPageData resultPageData = checkGroupService.findPage(pageParam);
        return resultPageData;
    }
}
