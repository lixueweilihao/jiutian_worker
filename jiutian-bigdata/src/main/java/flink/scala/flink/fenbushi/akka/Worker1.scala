package flink.scala.flink.fenbushi.akka

/**
  * Created on 2019-06-24
  *
  * @author :hao.li
  */

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class Worker1 extends Actor {
  //在Actor的构造方法之后，receive方法之前，执行一次
  override def preStart(): Unit = {
    //跟Master建立连接并通信
    val master: ActorSelection = context.actorSelection("akka.tcp://MasterActorSystem@localhost:8888/user/MasterA")
    //worker向Master发消息,这个发送消息实际上是拿到了Master的引用之后自己给自己发消息
    master ! "Register"
  }
  override def receive:Receive ={
    case "Response"=>{
      println("来自 Master的返回消息")
    }
  }
}
object Worker1{
  def main(args: Array[String]): Unit = {
    val host = "localhost"
//    val port = 8888
    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
       """.stripMargin
    val config = ConfigFactory.parseString(configStr)
    val actorSystem = ActorSystem("WorkerActorSystem",config)
    actorSystem.actorOf(Props[Worker1],"WorkerB")
  }
}

