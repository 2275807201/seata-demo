package com.liangh.seata.demo.business.service.impl;

import com.liangh.seata.demo.api.OrderService;
import com.liangh.seata.demo.api.StorageService;
import com.liangh.seata.demo.business.service.BusinessService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Author: heshouyou
 * @Description  Dubbo业务发起方逻辑
 * @Date Created in 2019/1/14 18:36
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    private boolean rollbackFlag = true;

    @Reference
    private StorageService storageService;

    @Reference
    private OrderService orderService;

    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-fescar-example")
    @Override
    public void purchase(String userId, String commodityCode, int orderCount) {

        orderService.create(userId,commodityCode,orderCount);
        storageService.deduct(commodityCode, orderCount);
        // 自定义回滚，测试全局事物
        if (rollbackFlag){
            throw new RuntimeException("自定义回滚，测试全局事物");
        }

    }
}
