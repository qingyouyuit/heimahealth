package com.itheima.health.controller;

import com.itheima.health.common.R;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.model.vos.MemberCountListVO;
import com.itheima.health.model.vos.SetmealReportVO;
import com.itheima.health.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private MemberService memberService;

    /**
     * 功能描述: 按月份查询会员数量
     *
     * @return : com.itheima.health.common.R
     */
    @GetMapping("/getMemberReport")
    public R getMemberCount() {
        try {
            MemberCountListVO memberCountVO = memberService.getMemberCount();
            return R.success(memberCountVO);
        } catch (Exception e) {
            return R.error(MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }

    /**
     * 功能描述: 查询套餐数量
     *
     * @return : com.itheima.health.common.R
     */
    @GetMapping("/getSetmealReport")
    public R getSetmealReportCount() {
        try {
            SetmealReportVO setmealReportVO = memberService.getSetmealReportCount();
            return R.success(setmealReportVO);
        } catch (Exception e) {
            return R.error(MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
    }
}
