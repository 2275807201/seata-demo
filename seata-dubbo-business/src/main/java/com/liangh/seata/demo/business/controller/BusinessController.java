package com.liangh.seata.demo.business.controller;

import com.liangh.seata.demo.business.service.BusinessService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: heshouyou
 * @Description  Dubbo业务执行入口
 * @Date Created in 2019/1/14 17:15
 */
@RestController
@Slf4j
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    /**
     * 模拟用户购买商品下单业务逻辑流程
     * @Param:
     * @Return:
     */
    @RequestMapping("/buy")
    public String handleBusiness(String userId,String commodityCode,int orderCount){
        businessService.purchase(userId,commodityCode,orderCount);
        return "ok";
    }

}
