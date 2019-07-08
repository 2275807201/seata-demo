package com.liangh.seata.demo.business.service.impl;

import com.liangh.seata.demo.api.AccountService;
import com.liangh.seata.demo.business.service.BusinessService;
import io.seata.core.context.RootContext;
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

    @Reference
    private AccountService accountService;

    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-fescar-example")
    @Override
    public void purchase(String userId, String commodityCode, int orderCount) {
        accountService.debit(userId,orderCount);
        throw new RuntimeException("hello,kankanyoumeiyou回滚");
    }
}
