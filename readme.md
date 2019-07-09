# SpringBoot + Apache Dubbo +  Seata

Integration SpringBoot + Apache Dubbo +  Seata

参考资料：
https://github.com/seata/seata-samples

### 1. prepare database 

create database （默认为：fescar_demo）

导入sql文件：seata-demo.sql

### 2. start Seata Server

download page：https://github.com/seata/seata/releases

download and unzip seata-server，cd the bin dictory, and run 

```bash
sudo sh seata-server.sh
```

### 3. start the demo module

start seata-dubbo-account、seata-dubbo-order、seata-dubbo-storage、seata-dubbo-business

### 4. start the normal request

use curl：

```bash
curl localhost:8081/buy\?userId=U100001\&commodityCode=C201901140001\&orderCount=2
```

### 5. test the rollback request

enter samples-business , change  BusinessServiceImpl, uncomment the following code ：

```
if (!flag) {
  throw new RuntimeException("测试抛异常后，分布式事务回滚！");
}
```

restart the  samples-business module, and execute the step 4.
