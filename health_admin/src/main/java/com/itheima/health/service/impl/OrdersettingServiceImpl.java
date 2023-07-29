package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.mapper.OrdersettingMapper;
import com.itheima.health.model.pojos.Ordersetting;
import com.itheima.health.model.vos.OrdersettingVO;
import com.itheima.health.service.OrdersettingService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrdersettingServiceImpl extends ServiceImpl<OrdersettingMapper, Ordersetting> implements OrdersettingService {
    @Autowired
    private OrdersettingMapper ordersettingMapper;

    /**
     * 功能描述: 根据月份查找数据
     *
     * @param month
     * @return : List<OrdersettingVO>
     */
    @Override
    public List<OrdersettingVO> OrdersettingList(String month) {
        List<OrdersettingVO> ordersettingVOList = ordersettingMapper.selectDateList(month);
        return ordersettingVOList;
    }
}
