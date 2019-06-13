# dtc-snmp-test实例
## 说明

&ensp;该项目为从远程主机中使用snmp协议获取一些系统指标信息。

## 测试说明
- SnmpData.java为方法类；<br />
- SnmpTest.java为测试类；<br />
主要操作是在SnmpTest.java类中执行的，具体注意事项如下：

  1. 以下所有测试方法中的ip需要换成已经打开snpm的测试机器的ip
  2. 需要保证需要测试的服务器打开161端口
  3. 测试类中有一些OID不一定存在，可按照文档中给定的OID进行测试
