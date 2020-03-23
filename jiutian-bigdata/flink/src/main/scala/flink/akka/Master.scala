package flink.scala.flink.akka

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
  * Created on 2019-06-24
  *
  * @author :hao.li
  */
class Master extends Actor{

  println("创建Master对象...")

  //初始化方法
  override def preStart(): Unit = {
    println("Master初始化...")
  }

  //不停地接收消息
  override def receive: Receive = {
    case "connect" => {
      println("一个Worker连接了.....")
      //向发送者返回消息
      sender ! "reply"
    }
  }

}

object Master {
  def main(args: Array[String]): Unit = {

    //连接地址和端口号
    val host = "localhost"
    val port = 8888
    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
      """.stripMargin
    //传入配置参数获取配置
    val config = ConfigFactory.parseString(configStr)
    //获取ActorSystem，指定名称和配置
    val masterSystem = ActorSystem("MasterSystem",config)
    //创建Actor
    val master = masterSystem.actorOf(Props(new Master),"master")
    //自己给自己发送给消息
    //    master ! "connect"
    masterSystem.awaitTermination()
  }
}
