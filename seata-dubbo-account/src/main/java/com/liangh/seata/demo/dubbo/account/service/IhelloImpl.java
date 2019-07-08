package com.liangh.seata.demo.dubbo.account.service;

import com.liangh.seata.demo.api.Ihello;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

@Slf4j
@Service
public class IhelloImpl implements Ihello {

    @Override
    public void sayHello() {
        log.info("生产者骄傲地说，hello,world");
    }
}
