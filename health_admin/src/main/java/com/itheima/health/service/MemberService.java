package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.model.pojos.Member;
import com.itheima.health.model.vos.MemberCountListVO;
import com.itheima.health.model.vos.SetmealReportVO;

public interface MemberService extends IService<Member> {
    /**
     * 功能描述: 查询会员数量
     *
     * @return : com.itheima.health.model.vos.MemberCountListVO
     */
    MemberCountListVO getMemberCount();


    /**
     * 功能描述: 查询套餐数量
     *
     * @return : com.itheima.health.model.vos.MemberCountListVO
     */
    SetmealReportVO getSetmealReportCount();
}
