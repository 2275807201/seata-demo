# SpringBoot + Apache Dubbo +  Seata

Integration SpringBoot + Apache Dubbo +  Seata

参考资料：
https://github.com/seata/seata-samples

### 2. prepare database 

create database （默认为：fescar_demo）

导入数据sql



3. start Nacos

Nacos quickstart：https://nacos.io/en-us/docs/quick-start.html

enter the  Nacos webconsole：http://127.0.0.1:8848/nacos/index.html

### 4. start Seata Server

download page：https://github.com/seata/seata/releases

download and unzip seata-server，cd the bin dictory, and run 

```bash
sh seata-server.sh 8091 file
```

### 5. start the demo module

start samples-account、samples-order、samples-storage、samples-business

use Nacos webconsole to ensure the registry is ok: http://127.0.0.1:8848/nacos/#/serviceManagement

> check the datasource config in application.properties is right.

### 6. start the normal request

use postman to send a post request：http://localhost:8104/business/dubbo/buy  

body：

```json
{
    "userId":"1",
    "commodityCode":"P190510529590122",
    "name":"fan",
    "count":2,
    "amount":"100"
}
```

or use curl：

```bash
curl localhost:8081/buy\?userId=U100001\&commodityCode=C201901140001\&orderCount=2
```

then this will send a pay request,and return code is 200

### 7. test the rollback request

enter samples-business , change  BusinessServiceImpl, uncomment the following code ：

```
if (!flag) {
  throw new RuntimeException("测试抛异常后，分布式事务回滚！");
}
```

restart the  samples-business module, and execute the step 6.
