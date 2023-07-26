package com.itheima.health.controller;

import com.itheima.health.common.PageParam;
import com.itheima.health.common.R;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.model.pojos.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import com.itheima.health.service.CheckItemService;
import lombok.Getter;
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

    /**
     * 功能描述: 新增检查组
     *
     * @param checkGroup
     * @param checkitemIds
     * @return : com.itheima.health.common.R
     */
    @PostMapping("/add")
    public R edit(@RequestBody CheckGroup checkGroup, @RequestParam Integer[] checkitemIds) {
        log.info("checkGroup:{}", checkGroup);
        log.info("checkitemIds:{}", checkitemIds);
        checkGroupService.edit(checkGroup, checkitemIds);
        return R.success("编辑成功");
    }

    /**
     * 功能描述: 回显检查组
     *
     * @param id
     * @return : com.itheima.health.common.R
     */
    @GetMapping("/findById")
    public R findById(@RequestParam Integer id) {
        CheckGroup checkGroup = checkGroupService.findById(id);
        return R.success("", checkGroup);
    }

    /**
     * 功能描述: 查询所有检查项
     *
     * @param
     * @return : com.itheima.health.common.R
     */
    @PostMapping("/edit")
    public R updata(@RequestBody CheckGroup checkGroup, @RequestParam Integer[] checkitemIds) {
        log.info("checkGroup:{}", checkGroup);
        log.info("checkitemIds:{}", checkitemIds);
        checkGroupService.updata(checkGroup, checkitemIds);
        return R.success("编辑成功");
    }

    @GetMapping("deleteById")
    public R deleteById(@RequestParam Integer id) {
        boolean flag = checkGroupService.deleteById(id);
        if (flag) {
            return R.success("删除成功");
        } else {
            return R.error("已关联,无法删除");
        }

    }
}

