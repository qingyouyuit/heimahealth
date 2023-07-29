package com.itheima.health.controller;

import com.itheima.health.common.R;
import com.itheima.health.model.vos.OrdersettingVO;
import com.itheima.health.service.OrdersettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class OrdersettingController {
    @Autowired
    private OrdersettingService ordersettingService;

    /**
     * 功能描述: 检查项目列表
     *
     * @param
     * @return : com.itheima.health.common.ResultPageData
     */

    @GetMapping("//getOrderSettingByMonth")
    public R list(@RequestParam("month") String month) {
        List<OrdersettingVO> list = ordersettingService.OrdersettingList(month);
        return R.success(list);
    }
}
