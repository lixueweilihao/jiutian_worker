## 功能：将数据打包成udp数据包，可以用作当网络隔离时的upd协议数据发送
## 使用方式：
- 首先将该项目打的jar包放到${flume_home}/lib下
- 然后在sink端配置
```sbtshell
agent1.sinks.udpsink.type=com.dtc.flume.UDPEventSink
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
 
agent1.sinks.udpsink.type=com.dtc.flume.UDPEventSink
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
