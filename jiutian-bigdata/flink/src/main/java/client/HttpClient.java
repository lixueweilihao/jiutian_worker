package client;



import client.builder.MetricBuilder;
import client.request.QueryBuilder;
import client.response.Response;
import client.response.SimpleHttpResponse;

import java.io.IOException;

public interface HttpClient extends Client {

	public Response pushMetrics(MetricBuilder builder,
								ExpectResponse exceptResponse) throws IOException;

	public SimpleHttpResponse pushQueries(QueryBuilder builder,
										  ExpectResponse exceptResponse) throws IOException;
}
