package com.itheima.health.controller;

import com.itheima.health.common.R;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.model.vos.OrdersettingVO;
import com.itheima.health.service.OrdersettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class OrdersettingController {
    @Autowired
    private OrdersettingService ordersettingService;

    /**
     * 功能描述: 检查项目列表
     *
     * @param month
     * @return : com.itheima.health.common.ResultPageData
     */

    @GetMapping("/getOrderSettingByMonth")
    public R list(@RequestParam("month") String month) {
        List<OrdersettingVO> list = ordersettingService.OrdersettingList(month);
        return R.success(list);
    }

    /**
     * 功能描述: 上传数据到数据库
     *
     * @param excelFile
     * @return : com.itheima.health.common.ResultPageData
     */
    @PostMapping("/upload")
    public R uploadexcel(@RequestParam("excelFile") MultipartFile excelFile) {
        try {
            ordersettingService.uploadData(excelFile);
            return R.success(MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            return R.error(MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }
}
