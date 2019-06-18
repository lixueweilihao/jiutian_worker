package flink.scala.opentsdb.client
import java.io.IOException

import com.google.common.base.Preconditions.checkNotNull
import com.google.gson.{Gson, GsonBuilder}
import flink.scala.opentsdb.client.ExpectResponse.ExpectResponse.ExpectResponse
import flink.scala.opentsdb.client.builder.MetricBuilder
import flink.scala.opentsdb.client.request.QueryBuilder
import flink.scala.opentsdb.client.response.{Response, SimpleHttpResponse}
import org.apache.commons.lang.StringUtils
import util.control.Breaks._
/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
object HttpClientImpl extends HttpClient{

  var serviceUrl:String = null

  var mapper:Gson = null

  private val httpClient = new PoolingHttpClient

  def HttpClientImpl(serviceUrl:String): Unit ={
    this.serviceUrl = serviceUrl
    val builder = new GsonBuilder
    mapper = builder.create

  }

  @throws[IOException]
 def pushMetrics(builder: MetricBuilder): Response = {
    pushMetrics(builder, ExpectResponse.ExpectResponse.STATUS_CODE)
  }

  def pushMetrics(builder: MetricBuilder, exceptResponse: ExpectResponse): Response = {
    pushMetrics(builder, ExpectResponse.ExpectResponse.STATUS_CODE)
  }

  @throws[IOException]
  def pushMetrics(builder: MetricBuilder, expectResponse: ExpectResponse): Response = {
    checkNotNull(builder)
    // TODO 错误处理，比如IOException或者failed>0，写到队列或者文件后续重试。
    val response:SimpleHttpResponse = httpClient.doPost(buildUrl(serviceUrl, PUT_POST_API, expectResponse), builder.build)
    getResponse(response)
  }

  def pushQueries(builder: QueryBuilder, exceptResponse: ExpectResponse): SimpleHttpResponse = ???

  /**
    * Sends metrics from the builder to the KairosDB server.
    *
    * @param builder
    * metrics builder
    * @return response from the server
    *
    *         problem occurred sending to the server
    */
  def pushMetrics(builder: MetricBuilder): Response = ???

  def pushQueries(builder: QueryBuilder): SimpleHttpResponse = ???

  private def buildUrl(serviceUrl: String, postApiEndPoint: String, expectResponse: ExpectResponse) = {
    var url = serviceUrl + postApiEndPoint
    expectResponse match {
      case ExpectResponse.ExpectResponse.SUMMARY =>
        url += "?summary"
        break() //todo: break is not supported
      case ExpectResponse.ExpectResponse.DETAIL =>
        url += "?details"
        break //todo: break is not supported
      case _ =>
        break //todo: break is not supported
    }
    url
  }

  private def getResponse(httpResponse: SimpleHttpResponse) = {
    val response = new Response(httpResponse.getStatusCode)
    val content = httpResponse.getContent
    if (StringUtils.isNotEmpty(content)) if (response.isSuccess) {
      val errorDetail = mapper.fromJson(content, classOf[ErrorDetail])
      response.setErrorDetail(errorDetail)
    }
    else logger.error("request failed!" + httpResponse)
    response
  }
}
