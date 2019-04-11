# dynamic-datasource

dynamic-datasource is a `DynamicDataSource` bean definition registrar, it will auto configure DataSource with Reading and Writing separation. You can use Master database to write and Slave database to read, The configurations like:
```
ds.mysql.zero.username=root
ds.mysql.zero.password=xxxxxxxx
ds.mysql.zero.rurl=jdbc:mysql://127.0.0.1:3306/zero?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8
ds.mysql.zero.wurl=jdbc:mysql://127.0.0.1:3306/zero?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8
ds.mysql.zero.mapperLocations=classpath:/mappers/zero/*.xml
ds.mysql.zero.basePackage=com.zero.mw.test.dal.zero.mapper
```
OR without Reading and Writing separation:
```
ds.mysql.sama.username=root
ds.mysql.sama.password=xxxxxxxx
ds.mysql.sama.url=jdbc:mysql://127.0.0.1:3306/sama?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8
ds.mysql.sama.mapperLocations=classpath:/mappers/sama/*.xml
ds.mysql.sama.basePackage=com.zero.mw.test.dal.sama.mapper
```


The dataSource pool using `Druid`, you can configure druid pool properties for default like:
```
ds.connection.default.name=default
ds.connection.default.driverClassName=com.mysql.cj.jdbc.Driver
ds.connection.default.initialSize=5
ds.connection.default.maxActive=8
ds.connection.default.minIdle=0
# deprecated
ds.connection.default.maxIdle=8     
ds.connection.default.maxWait=3000
ds.connection.default.notFullTimeoutRetryCount=0
ds.connection.default.validationQuery=null
ds.connection.default.validationQueryTimeout=-1
ds.connection.default.testOnBorrow=false
ds.connection.default.testOnReturn=false
ds.connection.default.testWhileIdle=false
ds.connection.default.poolPreparedStatements=false
ds.connection.default.sharePreparedStatements=false
ds.connection.default.timeBetweenEvictionRunsMillis=30000
ds.connection.default.numTestsPerEvictionRun=3
ds.connection.default.maxPoolPreparedStatementPerConnectionSize=10
ds.connection.default.minEvictableIdleTimeMillis=1800000
ds.connection.default.maxEvictableIdleTimeMillis=25200000
ds.connection.default.queryTimeout=10
ds.connection.default.transactionQueryTimeout=10
```

AND configure `zero` dataSource by oneself:
```
ds.connection.zero.name=zero
ds.connection.zero.driverClassName=com.mysql.cj.jdbc.Driver
ds.connection.zero.initialSize=5
ds.connection.zero.maxActive=8
ds.connection.zero.minIdle=0
ds.connection.zero.maxIdle=8
ds.connection.zero.maxWait=3000
ds.connection.zero.notFullTimeoutRetryCount=0
ds.connection.zero.validationQuery=null
ds.connection.zero.validationQueryTimeout=-1
ds.connection.zero.testOnBorrow=false
ds.connection.zero.testOnReturn=false
ds.connection.zero.testWhileIdle=false
ds.connection.zero.poolPreparedStatements=false
ds.connection.zero.sharePreparedStatements=false
ds.connection.zero.timeBetweenEvictionRunsMillis=30000
ds.connection.zero.numTestsPerEvictionRun=3
ds.connection.zero.maxPoolPreparedStatementPerConnectionSize=10
ds.connection.zero.minEvictableIdleTimeMillis=1800000
ds.connection.zero.maxEvictableIdleTimeMillis=25200000
ds.connection.zero.queryTimeout=5
ds.connection.zero.transactionQueryTimeout=10
```


