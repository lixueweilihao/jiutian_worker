package flink.scala.flink.fenbushi.akka.test.demo

/**
  * Created on 2019-06-24
  * https://blog.csdn.net/vinsuan1993/article/details/73351313
  * https://blog.csdn.net/qq_37050372/article/details/82343586#commentBox
  * @author :hao.li
  */

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.concurrent.duration._

class Master extends Actor{
  //在Master构造方法执行之后，receive方法之前要启动一个定时器
  val CHECK_INTERVAL = 15000
  //这里的key为workerid
  val id2Worker = new mutable.HashMap[String,WorkerInfo]()
  //这个是为了迭代方便才又定义的Set
  val workers = new mutable.HashSet[WorkerInfo]()

  override def preStart(): Unit = {
    //启动一个定时器
    //第一个参数为，第一次的延时为多少时间，这里使用的单位需要导包
    //第二个参数为每隔多少时间发一次
    //第三个参数为消息接收者的引用
    //第四个参数为Any类型的消息
    //该检查消息是Master内部的一个消息，相当于自己发给自己的一个消息
    import context.dispatcher //这个为隐式转换，没有会报错
    context.system.scheduler.schedule(0 millis,CHECK_INTERVAL millis,self,CheckTimeOutWorker )
  }

  //接收要处理的消息
  override def receive: Receive = {
    case "Register" => {
      println("来自Worker的注册消息")
      //给消息的发送者返回消息，sender是一个方法可以拿到消息发送者的引用
      sender ! "Response"
    }

    //Master发送给自己的一个内部检测消息
    case CheckTimeOutWorker=>{
      val current = System.currentTimeMillis()
      val deadworkers: mutable.HashSet[WorkerInfo] = workers.filter(w => current - w.lastHeartbeatinfo  > CHECK_INTERVAL)
//      遍历出每个死掉的worker然后从集合中移除
//            for(workinfo <- deadworkers) {
//              id2Worker -= workinfo.id
//              workers -= workinfo
//            }
      deadworkers.foreach(workinfo => {
        id2Worker -= workinfo.id
        println(workinfo.id+"lala")
        workers -= workinfo
      })
      println(workers.size)
    }
    //worker发送给master的注册消息
    case RegisterWorker(workerid,memory,cores) => {
      println("the worker {} come register.",workerid)
      //将Worker的信息封装起来然后保存
      val wi: WorkerInfo = new WorkerInfo(workerid,memory,cores)
      //保存到集合
      id2Worker(workerid) = wi
      //或者 id2Worker.put(workerid,wi)
      // id2Worker += ((workerid,wi))
      workers += wi
      sender() ! RegisteredWorker


    }
    //worker发送给master的心跳消息
    case Heartbeat(wordid) => {
      println(wordid+"send to heart")
      //根据id到id2worker这个map中查找对应的workerinfo
      val workerinfo: WorkerInfo = id2Worker(wordid)
      //更新时间
      val current = System.currentTimeMillis()
      workerinfo.lastHeartbeatinfo = current
    }
  }
}
object Master{
  val MASTER_ACTOR_SYSTEM_NAME = "MasterSystem"
  val MASTER_ACTOR_NAME = "Master"
  def main(args: Array[String]): Unit = {
    val host = "localhost"
    val port = 8888
    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configStr)
    val actorSystem = ActorSystem(MASTER_ACTOR_SYSTEM_NAME,config)
    val masterActor = actorSystem.actorOf(Props[Master],MASTER_ACTOR_NAME)
//    masterActor ! "CheckTimeOut"
  }
}



