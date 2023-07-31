package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.mapper.MemberMapper;
import com.itheima.health.model.pojos.Member;
import com.itheima.health.model.vos.MemberCountListVO;
import com.itheima.health.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 功能描述: 查询会员数量
     *
     * @return : com.itheima.health.model.vos.MemberCountListVO
     */
    @Override
    public MemberCountListVO getMemberCount() {
        // 1.查询会员数量
        List<HashMap<String, Object>> memberCount = memberMapper.getMemberCount();
        // 2.创建返回的VO对象
        MemberCountListVO memberCountListVO = new MemberCountListVO();
        // 3.创建两个集合
        List<String> months = new ArrayList<>();
        List<Integer> memberCounts = new ArrayList<>();
        // 4.遍历查询到的数据
        for (HashMap<String, Object> map : memberCount) {
            // 5.获取月份
            String monthsStr = map.get("months").toString();
            // 6.获取会员数量
            String memberCountInt = map.get("memberCount").toString();
            // 7.添加到集合中
            months.add(monthsStr);
            memberCounts.add(Integer.valueOf(memberCountInt));
        }
        // 8.设置到VO对象中
        memberCountListVO.setMonths(months);
        memberCountListVO.setMemberCount(memberCounts);
        // 9.返回
        return memberCountListVO;
    }
}
