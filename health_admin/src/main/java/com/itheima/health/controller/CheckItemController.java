package com.itheima.health.controller;

import com.itheima.health.common.R;
import com.itheima.health.config.BeanContext;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.model.dtos.CheckItemDto;
import com.itheima.health.model.pojos.CheckItem;
import com.itheima.health.service.CheckGroupService;
import com.itheima.health.service.CheckItemService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/checkitem")
public class CheckItemController {
    @Autowired
    private BeanContext beanContext;

    @Autowired
    private CheckItemService checkItemService;
    @Autowired
    private CheckGroupService checkGroupService;

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

    /**
     * 功能描述: 检查项回显
     *
     * @return : com.itheima.health.common.R
     */
    @GetMapping("/findById")
    public R chcheckitemFindById(@RequestParam("id") Integer id) {
        log.info("id:{}", id);
        CheckItem checkItem = checkItemService.findById(id);
        return R.success("查询成功", checkItem);
    }

    /**
     * 功能描述: 检查项修改
     *
     * @return : com.itheima.health.common.R
     */

    @PostMapping("/edit")
    public R chcheckitemEdit(@RequestBody CheckItem checkItem) {
        log.info("checkItem:{}", checkItem);
        checkItemService.updata(checkItem);
        return R.success("修改成功");
    }

    /**
     * 功能描述: 查询所有检查项
     *
     * @return : java.util.List<com.itheima.health.model.pojos.CheckItem>
     */
    @GetMapping("/findAll")
    public R findAll() {
        List<CheckItem> all = checkItemService.findAll();
        return R.success(all);
    }

    /**
     * 功能描述: 回显检查组对应的检查项
     *
     * @param id
     * @return : com.itheima.health.common.R
     */
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public R findCheckItemIdsByCheckGroupId(@RequestParam("checkgroupId") Integer checkgroupId) {
        List<Integer> checkItemIds = checkGroupService.findCheckItemIdsByCheckGroupId(checkgroupId);
        return R.success(checkItemIds);
    }
}
