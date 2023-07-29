package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.model.pojos.Ordersetting;
import com.itheima.health.model.pojos.OrdersettingData;
import com.itheima.health.model.vos.OrdersettingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersettingMapper extends BaseMapper<Ordersetting> {
    /**
     * 功能描述: 根据年份与月份查询每天的数据
     *
     * @param month
     * @return : void
     */
    List<OrdersettingVO> selectDateList(String month);

    /**
     * 功能描述: 批量导入ordersettings的数据
     *
     * @param ordersettings
     * @return : void
     */
    void batchInsert(List<OrdersettingData> ordersettings);
}
