//package java.hbase
//
//import org.apache.spark.{SparkConf, SparkContext}
//
//object WriteExample {
//  def main(args: Array[String]): Unit = {
//    val sc = new SparkContext(new SparkConf().setAppName("WriteExample").setMaster("spark://10.37.167.204:7077").setJars(List("D:/WorkerCode/play_demo_work/classes/artifacts/Spark_jar")))
//    val conf = HBaseConfiguration.create()
//    conf.set(HConstants.ZOOKEEPER_QUORUM, "namenode1-sit..com,namenode2-sit..com,slave01-sit..com")
//    conf.set(HConstants.ZOOKEEPER_CLIENT_PORT, "2015")
////    conf.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase")
//    conf.set(TableOutputFormat.OUTPUT_TABLE, "dataflow_test")
//    val job = Job.getInstance(conf)
//    job.setOutputKeyClass(classOf[ImmutableBytesWritable])
//    job.setOutputValueClass(classOf[Result])
//    job.setOutputFormatClass(classOf[TableOutputFormat[ImmutableBytesWritable]])
////    sc.parallelize(Array(("r1", 1), ("r2", 2), ("r3", 3))).map(
//    sc.parallelize(Array(("event", 1))).map(
//      x => {
//        val put = new Put(Bytes.toBytes(x._1))
//        put.add(Bytes.toBytes("f1"), Bytes.toBytes("c1"), Bytes.toBytes(x._2))
//        (new ImmutableBytesWritable, put)
//      }
//    ).saveAsNewAPIHadoopDataset(job.getConfiguration) //通过批量put来插入数据
//    sc.stop()
//  }
//
//}
