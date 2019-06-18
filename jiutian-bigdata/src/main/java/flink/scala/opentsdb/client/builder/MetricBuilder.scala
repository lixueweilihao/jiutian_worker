package flink.scala.opentsdb.client.builder

import java.io.IOException
import java.util
import java.util.{ArrayList, List}

import com.google.common.base.Preconditions.checkState
import com.google.gson.Gson

/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
class MetricBuilder {
  private val metrics = new util.ArrayList[Metric]
  private var mapper: Gson = null


  /**
    * Returns a new metric builder.
    *
    * @return metric builder
    */
  def getInstance = new MetricBuilder

  /**
    * Adds a metric to the builder.
    *
    * @param metricName
    * metric name
    * @return the new metric
    */
  def addMetric(metricName: String): Metric = {
    val metric = new Metric(metricName)
    metrics.add(metric)
    metric
  }

  /**
    * Returns a list of metrics added to the builder.
    *
    * @return list of metrics
    */
  def getMetrics: util.List[Metric] = metrics

  /**
    * Returns the JSON string built by the builder. This is the JSON that can
    * be used by the client add metrics.
    *
    * @return JSON
    * @throws IOException
    * if metrics cannot be converted to JSON
    */
  @throws[IOException]
  def build: String = {
    import scala.collection.JavaConversions._
    for (metric <- metrics) { // verify that there is at least one tag for each metric
      checkState(metric.getTags.size > 0, metric.getName + " must contain at least one tag.")
    }
    mapper.toJson(metrics)
  }

}
