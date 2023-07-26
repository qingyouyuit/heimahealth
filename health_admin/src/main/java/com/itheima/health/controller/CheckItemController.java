package com.itheima.health.controller;

import com.itheima.health.common.R;
import com.itheima.health.config.BeanContext;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.model.dtos.CheckItemDto;
import com.itheima.health.model.pojos.CheckItem;
import com.itheima.health.service.CheckItemService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/checkitem")
public class CheckItemController {
    @Autowired
    private BeanContext beanContext;

    @Autowired
    private CheckItemService checkItemService;

    /**
     * 功能描述: 检查项目列表
     *
     * @param pageParam
     * @return : com.itheima.health.common.ResultPageData
     */

    @PostMapping("/findPage")
    public ResultPageData findPage(@RequestBody PageParam pageParam) {
        // System.out.println(pageParam);
        //模拟数据
//        ResultPageData resultPageData = new ResultPageData();
//        resultPageData.setTotal(45);
//        resultPageData.setRows(new ArrayList());
        ResultPageData resultPageData = checkItemService.findPage(pageParam);
        return resultPageData;
    }

    /**
     * 功能描述: 检查项新增
     *
     * @return : com.itheima.health.common.R
     */
    @PostMapping("/add")
    public R chcheckitemAdd(@RequestBody CheckItemDto checkItemDto) {
        log.info("checkItemDto:{}", checkItemDto);
        checkItemService.add(checkItemDto);
        return R.success("新增成功");
    }

    /**
     * 功能描述: 检查项删除
     *
     * @return : com.itheima.health.common.R
     */
    @GetMapping("/delete")
    public R chcheckitemDelete(@RequestParam("id") Integer id) {
        log.info("id:{}", id);
        checkItemService.delete(id);
        return R.success("删除成功");
    }
}
