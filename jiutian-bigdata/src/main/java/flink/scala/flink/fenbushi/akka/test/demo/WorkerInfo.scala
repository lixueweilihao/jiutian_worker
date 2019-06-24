package flink.scala.flink.fenbushi.akka.test.demo

/**
  * Created on 2019-06-24
  *
  * @author :hao.li
  */

class WorkerInfo(val id:String,val memory:Int,val cores:Int ) {
  //缺少一个很重要的字段
  var lastHeartbeatinfo: Long = _
}

