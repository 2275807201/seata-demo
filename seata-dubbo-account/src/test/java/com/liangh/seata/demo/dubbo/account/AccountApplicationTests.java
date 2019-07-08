package com.liangh.seata.demo.dubbo.account;

import com.liangh.seata.demo.api.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccountService accountService;

    @Test
    public void contextLoads() {

        System.out.println(dataSource);

        accountService.debit("100",100);
    }

}
