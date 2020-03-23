# flume写upd数据并发送到下一级flume
## 使用方式：
- 首先将该项目打的jar包放到${flume_home}/lib下
- 然后在sink端配置
```sbtshell
agent1.sinks.udpsink.type=com.dtc.flume.sink.UDPEventSink
agent1.sinks.udpsink.channel=channel1
agent1.sinks.udpsink.udp.targetIp=<target IP>
agent1.sinks.udpsink.udp.targetPort=<target Port>
agent1.sinks.udpsink.udp.batchSize=<Batch size>
```
- 二级flume才采用netcatudp Source，具体见如下实例

## 实例
一级flume配置：
```sbtshell
agent1.sources=source1 
agent1.channels=channel1
agent1.sinks=udpsink  
  
# describe/configure source1  
agent1.sources.source1.type = TAILDIR
agent1.sources.source1.channels = channel1
agent1.sources.source1.channels.skipToEnd = True
agent1.sources.source1.positionFile = ./taildir_position.json
agent1.sources.source1.filegroups = f1
agent1.sources.source1.filegroups.f1 = /Users/lixuewei/logs/ac.log
agent1.sources.source1.headers.f1.headerKey1 = value1
agent1.sources.source1.fileHeader = true
  
agent1.channels.channel1.type=memory  
agent1.channels.channel1.capacity = 1000
agent1.channels.channel1.transactionCapacity = 1000
 
agent1.sinks.udpsink.type=com.dtc.flume.sink.UDPEventSink
agent1.sinks.udpsink.channel=channel1
agent1.sinks.udpsink.udp.targetIp=10.3.0.20
agent1.sinks.udpsink.udp.targetPort=8801
```
二级flume
```sbtshell
a1.sources = r1
a1.sinks = k1
a1.channels = c1

# Describe/configure the source
a1.sources.r1.type = netcatudp
a1.sources.r1.bind = 10.3.0.20
a1.sources.r1.port = 8801

# Describe the sink
a1.sinks.k1.type = logger

# Use a channel which buffers events in memory
a1.channels.c1.type = memory
a1.channels.c1.capacity = 1000
a1.channels.c1.transactionCapacity = 100

# Bind the source and sink to the channel
a1.sources.r1.channels = c1
a1.sinks.k1.channel = c1
```
# 使用mysql-source从mysql增量读取数据
## 配置文件
```sbtshell
a1.channels = ch-1
a1.sources = src-1
a1.sinks = k1


###########sql source1#################
# For each one of the sources, the type is defined
a1.sources.src-1.type = com.dtc.flume.source.metrics.SQLSource
a1.sources.src-1.hibernate.connection.url = jdbc:mysql://10.3.7.232:3306/zabbix
# Hibernate Database connection properties
a1.sources.src-1.hibernate.connection.user = zabbix
a1.sources.src-1.hibernate.connection.password = zabbix	
a1.sources.src-1.hibernate.connection.autocommit = true
a1.sources.src-1.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
a1.sources.src-1.hibernate.connection.driver_class = com.mysql.jdbc.Driver
a1.sources.src-1.run.query.delay=5000
#/home/flume/software/flume/src-1目录要提前建好
a1.sources.src-1.status.file.path = /opt/flume-1.8.0/data
#/home/flume/software/flume/src-1/sqlSource.status该文件是记录最大的id的，此后按照比他大的id作为offset来获取新数据，因为需要增加一个自增id（如果有自增的键也可以不设自增id）
a1.sources.src-1.status.file.name = sqlSource.status
# Custom query
a1.sources.src-1.start.from = 0
# sql语句
a1.sources.src-1.custom.query =  select a.id id,c.ip ip,c.host host,a.value value,a.clock clock,a.ns ns,b.name name,b.key_ english, b.snmp_oid oid from history a JOIN items b on b.itemid = a.itemid JOIN (select DISTINCT d.hostid hostid ,d.ip ip ,e.host host from interface d JOIN hosts e on d.hostid=e.hostid) c on b.hostid = c.hostid where a.id > $@$ order by a.id asc


a1.sources.src-1.batch.size = 1000
a1.sources.src-1.max.rows = 1000
a1.sources.src-1.hibernate.connection.provider_class = org.hibernate.connection.C3P0ConnectionProvider
a1.sources.src-1.hibernate.c3p0.min_size=1
a1.sources.src-1.hibernate.c3p0.max_size=10

################################################################
a1.channels.ch-1.type = memory
a1.channels.ch-1.capacity = 10000rmp
a1.channels.ch-1.transactionCapacity = 10000
a1.channels.ch-1.byteCapacityBufferPercentage = 20
a1.channels.ch-1.byteCapacity = 800000


################################################################
a1.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink
a1.sinks.k1.topic = zabbix_test
a1.sinks.k1.brokerList = 10.3.7.232:9092,10.3.7.233:9092,10.3.7.234:9092
a1.sinks.k1.requiredAcks = 1
a1.sinks.k1.batchSize = 20
a1.sinks.k1.channel = ch-1
a1.sources.src-1.channels=ch-1

```
