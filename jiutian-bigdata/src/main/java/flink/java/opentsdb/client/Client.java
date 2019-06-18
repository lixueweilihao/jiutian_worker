package flink.java.opentsdb.client;

import flink.java.opentsdb.client.builder.MetricBuilder;
import flink.java.opentsdb.client.request.QueryBuilder;
import flink.java.opentsdb.client.response.Response;
import flink.java.opentsdb.client.response.SimpleHttpResponse;

import java.io.IOException;

public interface Client {

	String PUT_POST_API = "/api/put";

    String QUERY_POST_API = "/api/query";

	/**
	 * Sends metrics from the builder to the KairosDB server.
	 *
	 * @param builder
	 *            metrics builder
	 * @return response from the server
	 * @throws IOException
	 *             problem occurred sending to the server
	 */
	Response pushMetrics(MetricBuilder builder) throws IOException;

	SimpleHttpResponse pushQueries(QueryBuilder builder) throws IOException;
}
