package com.itheima.health.service;


import com.itheima.health.mapper.CheckgroupCheckitemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author author
 * @since 2023-07-26
 */
@Service
public class CheckgroupCheckitemService {
    @Autowired
    private CheckgroupCheckitemMapper checkgroupCheckitemDao;

}
