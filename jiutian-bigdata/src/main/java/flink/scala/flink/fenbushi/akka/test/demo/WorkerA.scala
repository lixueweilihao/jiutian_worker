package flink.scala.flink.fenbushi.akka.test.demo

/**
  * Created on 2019-08-26
  *
  * @author :hao.li
  */
package flink.scala.flink.fenbushi.akka.test.demo

/**
  * Created on 2019-06-24
  *
  * @author :hao.li
  */
import java.util.UUID

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._
class WorkerA(val host:String,val port:Int,val memory:Int,val cores:Int) extends Actor {
  //在Actor的构造方法之后，receive方法之前，执行一次
  val WORKER_ID = UUID.randomUUID().toString
  val HEARTBEAT_INTERVAL = 10000
  var masterRef: ActorSelection = _
  override def preStart(): Unit = {
    //跟Master建立连接并通信
    val master: ActorSelection = context.actorSelection(s"akka.tcp://${Master.MASTER_ACTOR_SYSTEM_NAME}@$host:$port/user/${Master.MASTER_ACTOR_NAME}")
    masterRef = master
    //worker向Master发消息,这个发送消息实际上是拿到了Master的引用之后自己给自己发消息
    //发送注册消息，（worker的id,worker的内存，cpu的总核数）
    master ! RegisterWorker(WORKER_ID,memory,cores)
  }
  override def receive:Receive ={
    case "Response"=>{
      println("来自 Master的返回消息")
    }
    case RegisteredWorker => {
      //启动一个定时器，定期向Master发送心跳
      import context.dispatcher
      context.system.scheduler.schedule(0 millis,HEARTBEAT_INTERVAL millis,self,SendHeartbeat)
    }
    case SendHeartbeat => {
      //在该case中可以进行一些逻辑判断
      masterRef ! Heartbeat(WORKER_ID)
    }
  }
}
object WorkerA{
  val WORKER_ACTOR_SYSTEM_NAME = "WorkerSystem"
  val WORKER_ACTOR_NAME = "Worker"


  def main(args: Array[String]): Unit = {
    val workerHost = "localhost"
    val workerPort = 8890
    val masterHost = "localhost"
    val masterPort = 8888
    val workerMemory = 4
    val workerCores = 2
    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$workerHost"
         |akka.remote.netty.tcp.port = "$workerPort"
       """.stripMargin
    val config = ConfigFactory.parseString(configStr)
    val actorSystem = ActorSystem(WORKER_ACTOR_SYSTEM_NAME,config)
    actorSystem.actorOf(Props(new Worker(masterHost,masterPort,workerMemory,workerCores)),WORKER_ACTOR_NAME)
  }
}


