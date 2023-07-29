package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.model.pojos.Ordersetting;
import com.itheima.health.model.vos.OrdersettingVO;

import java.util.List;

public interface OrdersettingService extends IService<Ordersetting> {
    List<OrdersettingVO> OrdersettingList(String month);
}
