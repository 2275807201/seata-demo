package com.liangh.seata.demo.business;

import com.liangh.seata.demo.api.Ihello;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessApplicationTest {

    @Reference
    private Ihello helloService;


    @Test
    public void contextLoads() {


        log.info("调用生产者");
        helloService.sayHello();

    }

}