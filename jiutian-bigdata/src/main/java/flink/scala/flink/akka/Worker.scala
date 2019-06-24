package flink.scala.flink.akka
import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import flink.scala.flink.fenbushi.akka.Worker1


/**
  * Created on 2019-06-24
  *
  * @author :hao.li
  */
class Worker extends Actor{

  var master : ActorSelection =_

  println("创建Worker对象...")

  override def preStart(): Unit = {
    //与Master建立连接，拿到master引用
    master = context.actorSelection("akka.tcp://MasterSystem@localhost:8888/user/master")
    //向Master发送消息
    master ! "connect"
  }

  override def receive: Receive = {
    case "reply" => {
      println("收到Master消息")
    }
  }
}

object Worker {
  def main(args: Array[String]): Unit = {
    val host = "localhost"
//    val port = 8888
    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
      """.stripMargin
    //传入配置参数获取配置
    val config = ConfigFactory.parseString(configStr)
    //获取ActorSystem，指定名称和配置
    val workerSystem = ActorSystem("WorkerSystem",config)
    //创建Actor
    workerSystem.actorOf(Props[Worker],"WorkerB")
//    val actor = workerSystem.actorOf(Props(new Worker(host,port)),"worker")
    workerSystem.awaitTermination()
  }
}
