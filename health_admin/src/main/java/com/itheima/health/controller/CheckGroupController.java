package com.itheima.health.controller;

import com.itheima.health.common.PageParam;
import com.itheima.health.common.R;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.model.pojos.CheckGroup;
import com.itheima.health.service.impl.CheckGroupService;
import com.itheima.health.service.impl.CheckItemService;
import com.itheima.health.service.impl.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    private SetmealService setmealService;

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
        try {
            checkGroupService.edit(checkGroup, checkitemIds);
            return R.success(MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return R.error(MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    /**
     * 功能描述: 回显检查组
     *
     * @param id
     * @return : com.itheima.health.common.R
     */
    @GetMapping("/findById")
    public R findById(@RequestParam Integer id) {
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return R.success(MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
        } catch (Exception e) {
            return R.error(MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 功能描述: 更新所有检查项
     *
     * @param
     * @return : com.itheima.health.common.R
     */
    @PostMapping("/edit")
    public R updata(@RequestBody CheckGroup checkGroup, @RequestParam Integer[] checkitemIds) {
        log.info("checkGroup:{}", checkGroup);
        log.info("checkitemIds:{}", checkitemIds);
        try {
            checkGroupService.updata(checkGroup, checkitemIds);
            return R.success(MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return R.error(MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    /**
     * 功能描述: 根据id删除检查组，如果有检查组与检查项关联，无法删除
     *
     * @param id
     * @return : com.itheima.health.common.R
     */
    @GetMapping("deleteById")
    public R deleteById(@RequestParam Integer id) {
        boolean flag = checkGroupService.deleteById(id);
        if (flag) {
            return R.success(MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } else {
            return R.error(MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    /**
     * 功能描述: 查询所有的检查组并返回
     *
     * @return : com.itheima.health.common.R
     */
    @GetMapping("/findAll")
    public R findAllCheckGroup() {
        try {
            List<CheckGroup> checkGroupList = checkGroupService.findAll();
            return R.success(MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroupList);
        } catch (Exception e) {
            return R.success(MessageConstant.QUERY_CHECKGROUP_FAIL);
        }

    }

    /**
     * 功能描述:  根据套餐id查询检查组id
     *
     * @param setmealId
     * @return : com.itheima.health.common.R
     */
    @GetMapping("/findCheckGroupIdBysetmealId")
    public R findCheckGroupIdBysetmealId(@RequestParam("setmealId") Integer setmealId) {
        List<Integer> checkGroupId = setmealService.findCheckGroupIdBysetmealId(setmealId);
        return R.success(checkGroupId);
    }
}

