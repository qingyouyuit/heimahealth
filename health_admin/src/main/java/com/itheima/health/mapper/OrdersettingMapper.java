package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.model.pojos.Ordersetting;
import com.itheima.health.model.vos.OrdersettingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersettingMapper extends BaseMapper<Ordersetting> {

    List<OrdersettingVO> selectDateList(String month);
}
