package flink.scala.opentsdb.client

import java.io.IOException

import flink.java.opentsdb.client.ExpectResponse
import flink.java.opentsdb.client.builder.MetricBuilder
import flink.java.opentsdb.client.request.QueryBuilder
import flink.java.opentsdb.client.response.{Response, SimpleHttpResponse}

/**
  * Created on 2019-06-17
  *
  * @author :hao.li
  */
trait HttpClient extends Client {
  @throws[IOException]
  def pushMetrics(builder: MetricBuilder, exceptResponse: ExpectResponse): Response

  @throws[IOException]
  def pushQueries(builder: QueryBuilder, exceptResponse: ExpectResponse): SimpleHttpResponse

}
