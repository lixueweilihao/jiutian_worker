package com.flink.demo.scala


import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.streaming.api.functions.co.{CoFlatMapFunction, CoMapFunction}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.util.Collector


object Main {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val dataStream = env.fromElements(1, 2, 3, 4, 5).map { t: Int => t }
    val iterated = dataStream.iterate((input: ConnectedStreams[Int, String]) => {
      val head = input.map(i => (i + 1).toString, s => s)
      (head.filter(_ == "2"), head.filter(_ != "2"))
    }, 100)
    iterated.print
    env.execute("Window Stream WordCount")
  }
}
