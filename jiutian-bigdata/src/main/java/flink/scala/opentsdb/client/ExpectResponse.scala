package flink.scala.opentsdb.client

/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
object ExpectResponse {
  object ExpectResponse extends Enumeration{
    type ExpectResponse =Value
    val STATUS_CODE, SUMMARY, DETAIL =Value
  }

}
