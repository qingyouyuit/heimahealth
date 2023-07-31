package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.model.pojos.Member;
import com.itheima.health.model.vos.MemberCountListVO;

import java.util.HashMap;
import java.util.List;

public interface MemberMapper extends BaseMapper<Member> {
    /**
     * 功能描述: 按照年份查找会员数量
     *
     * @return : java.util.HashMap<java.lang.String,java.lang.Object>
     */
    List<HashMap<String, Object>> getMemberCount();

}
