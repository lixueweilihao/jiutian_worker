####  Java jdk > 1.7
####  api 是业务接口的定义 其中定义了 HelloService
####  server 是业务接口的实现，maven 依赖 api 模块 和 rpc-frame
#### rpc-frame 是模拟实现一个rpc框架
#### client 是模拟客户端，maven 依赖api 和 rpc-frame

> 思路：

* 从下面几个方面思考，仅供参考：

* 通信模型：假设通信的为A机器与B机器，A与B之间有通信模型，在Java中一般基于BIO或NIO；。

* 过程（服务）定位：使用给定的通信方式，与确定IP与端口及方法名称确定具体的过程或方法；

* 远程代理对象：本地调用的方法(服务)其实是远程方法的本地代理，因此可能需要一个远程代理对象，对于Java而言，远程代理对象可以使用Java的动态对象实现，封装了调用远程方法调用；

* 序列化，将对象名称、方法名称、参数等对象信息进行网络传输需要转换成二进制传输，这里可能需要不同的序列化技术方案。如:protobuf，Arvo等。

博客：https://www.jianshu.com/p/643f1e5157a4