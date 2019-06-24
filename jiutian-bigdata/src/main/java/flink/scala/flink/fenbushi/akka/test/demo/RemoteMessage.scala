package flink.scala.flink.fenbushi.akka.test.demo

/**
  * Created on 2019-06-24
  *
  * @author :hao.li
  */
trait RemoteMessage extends Serializable

//worker->Master
case class RegisterWorker(id:String , memory : Int ,cores:Int) extends RemoteMessage
case class Heartbeat(id:String ) extends RemoteMessage

//Master->worker
case class RegisteredWorker(masterUrl: String) extends RemoteMessage
//worker->self
case object SendHeartbeat
//Master->self
case object CheckTimeOutWorker

