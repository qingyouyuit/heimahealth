package com.itheima.health.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.itheima.health.mapper.OrdersettingMapper;
import com.itheima.health.model.pojos.OrdersettingData;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrdersettingListener implements ReadListener<OrdersettingData> {
    private static final int BATCH_COUNT = 100;

    private OrdersettingMapper ordersettingMapper;
    private List<OrdersettingData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);


    public OrdersettingListener(OrdersettingMapper ordersettingMapper) {
        this.ordersettingMapper = ordersettingMapper;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(OrdersettingData data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        ordersettingMapper.batchInsert(cachedDataList);
        log.info("存储数据库成功！");
    }

}
