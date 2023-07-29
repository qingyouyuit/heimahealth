package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.common.PageParam;
import com.itheima.health.common.ResultPageData;
import com.itheima.health.mapper.SetmealMapper;
import com.itheima.health.model.pojos.Setmeal;
import com.itheima.health.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    public static final String SETMEAL_ID = "setmeal_id";
    public static final String CHECKGROUP_ID = "checkgroup_id";
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 功能描述: 分页查询
     *
     * @param pageParam
     * @return : com.itheima.health.common.ResultPageData
     */
    @Override
    public ResultPageData findPage(PageParam pageParam) {
        Integer pageSize = pageParam.getPageSize();
        Integer currentPage = pageParam.getCurrentPage();

        Page<Setmeal> setmealPage = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();

        if (!StringUtils.isEmpty(pageParam.getQueryString())) {
            if (pageParam.getQueryString().matches("^[0-9]*$")) {
                queryWrapper.like(Setmeal::getCode, pageParam.getQueryString());
            } else if (pageParam.getQueryString().matches("^[A-Z]*$")) {
                queryWrapper.like(Setmeal::getHelpCode, pageParam.getQueryString());
            } else {
                queryWrapper.like(Setmeal::getName, pageParam.getQueryString());
            }
        }
        Page page = setmealMapper.selectPage(setmealPage, queryWrapper);

        ResultPageData resultPageData = new ResultPageData();
        resultPageData.setRows(page.getRecords());
        resultPageData.setTotal(page.getTotal());
        return resultPageData;
    }

    /**
     * 功能描述: 添加套餐
     *
     * @param setmeal
     * @param checkgroupIds
     * @return : void
     */
    @Override
    public void addSetmeal(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealMapper.insert(setmeal);
        setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
    }


    /**
     * 功能描述: 查询所有套餐
     *
     * @return : java.util.List<com.itheima.health.model.pojos.Setmeal>
     */
    @Override
    public List<Setmeal> findAll() {
        return setmealMapper.selectList(null);
    }


    /**
     * 功能描述: 根据id查询套餐
     *
     * @param id
     * @return : com.itheima.health.model.pojos.Setmeal
     */
    @Override
    public Setmeal findById(Integer id) {
        return setmealMapper.selectById(id);
    }

    /**
     * 功能描述: 根据套餐id查询检查组id
     *
     * @param setmealId
     * @return : java.util.List<java.lang.Integer>
     */
    @Override
    public List<Integer> findCheckGroupIdBysetmealId(Integer setmealId) {
        return setmealMapper.findSetmeaIdByCheckGroupId(setmealId);
    }

    /**
     * 功能描述: 修改套餐
     *
     * @param setmeal
     * @param checkgroupIds
     * @return : void
     */
    @Override
    public void updata(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealMapper.updateById(setmeal);
        // 删除检查组对应的检查项
        setmealMapper.deleteCheckGroupAndCheckItemByCheckGroupId(setmeal.getId());
        // 添加检查组对应的检查项
        setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
    }

    /**
     * 功能描述: 删除套餐
     *
     * @param id
     * @return : boolean
     */
    @Override
    public boolean deleteById(Integer id) {
        // 判断是否有检查项关联
        if (setmealMapper.findSetmeaIdByCheckGroupId(id).size() > 0) {
            return false;
        }
        // 删除检查组
        setmealMapper.deleteById(id);
        return true;
    }

    /**
     * 功能描述: 添加套餐和检查组的关系
     *
     * @param id
     * @param checkgroupIds
     * @return : void
     */
    private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            for (Integer checkgroupId : checkgroupIds) {
                HashMap<String, Integer> hashMap = new HashMap<>();
                hashMap.put(SETMEAL_ID, id);
                hashMap.put(CHECKGROUP_ID, checkgroupId);
                setmealMapper.setSetmealAndCheckGroup(hashMap);
            }
        }
    }
}
