package com.itheima.health.controller;

import com.itheima.health.common.R;
import com.itheima.health.config.BeanContext;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.model.dtos.CheckItemDto;
import com.itheima.health.model.pojos.CheckItem;
import com.itheima.health.service.impl.CheckGroupService;
import com.itheima.health.service.impl.CheckItemService;
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
        try {
            checkItemService.add(checkItemDto);
            return R.success(MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            return R.error(MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }

    /**
     * 功能描述: 检查项删除
     *
     * @return : com.itheima.health.common.R
     */
    @GetMapping("/delete")
    public R chcheckitemDelete(@RequestParam("id") Integer id) {
        log.info("id:{}", id);
        try {
            checkItemService.delete(id);
            return R.success(MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            return R.error(MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    /**
     * 功能描述: 检查项回显
     *
     * @return : com.itheima.health.common.R
     */
    @GetMapping("/findById")
    public R chcheckitemFindById(@RequestParam("id") Integer id) {
        log.info("id:{}", id);
        try {
            CheckItem checkItem = checkItemService.findById(id);
            return R.success(MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
        } catch (Exception e) {
            return R.error(MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    /**
     * 功能描述: 检查项修改
     *
     * @return : com.itheima.health.common.R
     */

    @PostMapping("/edit")
    public R chcheckitemEdit(@RequestBody CheckItem checkItem) {
        log.info("checkItem:{}", checkItem);
        try {
            checkItemService.updata(checkItem);
            return R.success(MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            return R.error(MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    /**
     * 功能描述: 查询所有检查项
     *
     * @return : java.util.List<com.itheima.health.model.pojos.CheckItem>
     */
    @GetMapping("/findAll")
    public R findAll() {
        List<CheckItem> checkItemList = checkItemService.findAll();
        return R.success(checkItemList);
    }

    /**
     * 功能描述: 回显检查组对应的检查项
     *
     * @param checkgroupId
     * @return : com.itheima.health.common.R
     */
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public R findCheckItemIdsByCheckGroupId(@RequestParam("checkgroupId") Integer checkgroupId) {
        List<Integer> checkItemIds = checkGroupService.findCheckItemIdsByCheckGroupId(checkgroupId);
        return R.success(checkItemIds);
    }
}
