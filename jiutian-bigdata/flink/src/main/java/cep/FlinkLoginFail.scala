//package com.dtc.scala.analytic.demo
//
///**
//  * Created on 2019-09-12
//  *
//  * @author :hao.li
//  */
//
//import org.apache.flink.cep.CEP
//import org.apache.flink.cep.PatternStream
//import org.apache.flink.cep.pattern.Pattern
//import org.apache.flink.cep.pattern.conditions.IterativeCondition
//import org.apache.flink.streaming.api.datastream.DataStream
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
//import org.apache.flink.streaming.api.windowing.time.Time
//import java.util
//
//object FlinkLoginFail {
//  @throws[Exception]
//  def main(args: Array[String]): Unit = {
//    val env = StreamExecutionEnvironment.getExecutionEnvironment
//    val loginEventStream = env.fromCollection(util.Arrays.asList(new Nothing("1", "192.168.0.1", "fail"), new Nothing("1", "192.168.0.2", "fail"), new Nothing("1", "192.168.0.3", "fail"), new Nothing("2", "192.168.10,10", "success")))
//    val loginFailPattern = Pattern.begin[Nothing]("begin").where(new Nothing() {
//      @throws[Exception]
//      def filter(loginEvent: Nothing, context: Nothing): Boolean = loginEvent.getType.equals("fail")
//    }).next("next").where(new Nothing() {
//      @throws[Exception]
//      def filter(loginEvent: Nothing, context: Nothing): Boolean = loginEvent.getType.equals("fail")
//    }).within(Time.seconds(3))
//    val patternStream = CEP.pattern(loginEventStream.keyBy(LoginEvent.getUserId), loginFailPattern)
//    val loginFailDataStream = patternStream.select((pattern: util.Map[String, util.List[Nothing]]) => {
//      def foo(pattern: util.Map[String, util.List[Nothing]]) = {
//        val first = pattern.get("begin")
//        val second = pattern.get("next")
//        new Nothing(second.get(0).getUserId, second.get(0).getIp, second.get(0).getType)
//      }
//
//      foo(pattern)
//    })
//    loginFailDataStream.print
//    env.execute
//  }
//}
