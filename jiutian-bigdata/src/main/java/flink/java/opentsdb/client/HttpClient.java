package flink.java.opentsdb.client;



import flink.java.opentsdb.client.builder.MetricBuilder;
import flink.java.opentsdb.client.request.QueryBuilder;
import flink.java.opentsdb.client.response.Response;
import flink.java.opentsdb.client.response.SimpleHttpResponse;

import java.io.IOException;

public interface HttpClient extends Client {

	public Response pushMetrics(MetricBuilder builder,
								ExpectResponse exceptResponse) throws IOException;

	public SimpleHttpResponse pushQueries(QueryBuilder builder,
										  ExpectResponse exceptResponse) throws IOException;
}
