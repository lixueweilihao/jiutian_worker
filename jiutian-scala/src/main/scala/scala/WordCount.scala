package scala

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
//    if (args.length < 1) {
//      System.err.print("Usage: <file>")
//      System.exit(1)
//    }
    val conf = new SparkConf().setAppName("aaa").setMaster("spark://10.37.167.204:7077")
    val sc = new SparkContext(conf)
    val line = sc.textFile("hdfs://10.37.167.204:9000/user/connect/logs/words")
    line.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).collect().foreach(println)
    print("abc")
    sc.stop()
  }

}
