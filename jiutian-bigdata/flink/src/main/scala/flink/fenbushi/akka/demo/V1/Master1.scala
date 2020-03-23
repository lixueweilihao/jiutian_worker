package flink.scala.flink.fenbushi.akka.demo.V1

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
  * Created on 2019-06-24
  *
  * @author :hao.li
  */

class Master1 extends Actor {
  println("constructor invoked")

  //接收要处理的消息
  override def receive: Receive = {
    case "Register" => {
      println("来自Worker的注册消息")
      //给消息的发送者返回消息，sender是一个方法可以拿到消息发送者的引用
      sender ! "Response"
    }
    case "HeartBeat" => {

    }
    case "CheckTimeOut" => {
      println("internal msg check")
    }
  }
}

object Master1 {
  def main(args: Array[String]): Unit = {
    val host = "192.168.1.5"
    val port = 8888
    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configStr)
    val actorSystem = ActorSystem("MasterActorSystem", config)
    val masterActor = actorSystem.actorOf(Props[Master1], "MasterA")
    masterActor ! "CheckTimeOut"
  }
}
