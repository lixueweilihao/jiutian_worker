//package flink.scala.opentsdb.client
//
//
//import flink.scala.opentsdb.client.builder.MetricBuilder
//import flink.scala.opentsdb.client._
//
///**
//  * Created on 2019-06-19
//  *ArrayList
//  * @author :hao.li
//  */
//object Demo {
//  def main(args:Array[String]): Unit ={
//    val client = new HttpClientImpl
//    client.HttpClientImpl("http://192.168.1.7:4242")
//    val builder = new MetricBuilder
//    builder.addMetric("metric2").setDataPoint(200L).addTag("lihao", "tab1value").addTag("tag2", "tab2value")
//    val response = client.pushMetrics(builder,ExpectResponse.SUMMARY)
//    System.out.println(response)
//  }
//
//}
