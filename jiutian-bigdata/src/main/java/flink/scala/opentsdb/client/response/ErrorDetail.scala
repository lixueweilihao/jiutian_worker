package flink.scala.opentsdb.client.response

import java.util
import java.util.{Collections, HashMap, List, Map}

import flink.java.opentsdb.client.response.ErrorDetail

/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
class ErrorDetail {
  private var errors:util.List[ErrorDetail.ErrorDetailEntity] = null

  private var success:Integer = null
  private var failed:Integer = null

  def this(errors: util.List[ErrorDetail.ErrorDetailEntity]) {
    this()
    this.errors = errors
  }

  def this(success: Integer, failed: Integer) {
    this()
    this.success = success
    this.failed = failed
  }

  def this(success: Integer, failed: Integer, errors: util.List[ErrorDetail.ErrorDetailEntity]) {
    this()
    this.success = success
    this.failed = failed
    this.errors = errors
  }

  def this(error: ErrorDetail.ErrorDetailEntity) {
    this()
    errors = Collections.singletonList(error)
  }

  def getErrors: util.List[ErrorDetail.ErrorDetailEntity] = errors

  def getSuccess: Integer = success

  def setSuccess(success: Integer): Unit = {
    this.success = success
  }

  def getFailed: Integer = failed

  def setFailed(failed: Integer): Unit = {
    this.failed = failed
  }

  class ErrorDetailEntity {
    private var datapoint:ErrorDetail.DataPoint = null
    private var error:String = null

    def getDatapoint: ErrorDetail.DataPoint = datapoint

    def setDatapoint(datapoint: ErrorDetail.DataPoint): Unit = {
      this.datapoint = datapoint
    }

    def getError: String = error

    def setError(error: String): Unit = {
      this.error = error
    }

    override def toString: String = "ErrorDetailEntity [datapoint=" + datapoint + ", error=" + error + "]"
  }

  class DataPoint {
    private var metric:String = null
    private var timestamp:Long = 0L
    private var value:Any = null
    private var tags:util.Map[String, String] = new util.HashMap[String, String]

    def getMetric: String = metric

    def setMetric(metric: String): Unit = {
      this.metric = metric
    }

    def getTimestamp: Long = timestamp

    def setTimestamp(timestamp: Long): Unit = {
      this.timestamp = timestamp
    }

    def getValue: Any = value

    def setValue(value: Any): Unit = {
      this.value = value
    }

    def getTags: util.Map[String, String] = tags

    def setTags(tags: util.Map[String, String]): Unit = {
      this.tags = tags
    }
  }

  override def toString: String = "ErrorDetail [" + "success=" + success + ", failed=" + failed + ", errors=" + errors + "]"

}
