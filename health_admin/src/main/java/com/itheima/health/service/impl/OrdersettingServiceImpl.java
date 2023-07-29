package com.itheima.health.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.listener.OrdersettingListener;
import com.itheima.health.mapper.OrdersettingMapper;
import com.itheima.health.model.pojos.Ordersetting;
import com.itheima.health.model.pojos.OrdersettingData;
import com.itheima.health.model.vos.OrdersettingVO;
import com.itheima.health.service.OrdersettingService;
import groovy.util.logging.Slf4j;
import io.netty.util.internal.ObjectUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
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

    /**
     * 功能描述: 上传excel表导入数据库
     *
     * @param excelFile
     *
     */
    @Override
    public void uploadData(MultipartFile excelFile) {
        if (excelFile.getSize() <= 0 || excelFile.isEmpty()) {
            throw new RuntimeException();
        }
        try {
            InputStream excelFileInputStream = excelFile.getInputStream();
            EasyExcel.read(excelFileInputStream, OrdersettingData.class,
                            new OrdersettingListener(ordersettingMapper))
                    .sheet()
                    .doRead();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
