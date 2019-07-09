/*
 *  Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.liangh.seata.demo.dubbo.order.service;

import com.liangh.seata.demo.api.AccountService;
import com.liangh.seata.demo.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Please add the follow VM arguments:
 * <pre>
 *     -Djava.net.preferIPv4Stack=true
 * </pre>
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Reference
    private AccountService accountService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String userId, String commodityCode, int orderCount) {

        // 计算订单金额
        int orderMoney = calculate(commodityCode, orderCount);

        // 从账户余额扣款
        accountService.debit(userId, orderMoney);

        // 创建新的订单
        String sql = "insert into order_tbl (user_id, commodity_code, count, money) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql,userId,commodityCode,orderCount,orderMoney);

        log.info("创建新的订单done");

    }



    private int calculate(String commodityId, int orderCount) {
        return 1 * orderCount;
    }

}
