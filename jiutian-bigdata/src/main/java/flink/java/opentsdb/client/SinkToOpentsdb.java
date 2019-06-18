package flink.java.opentsdb.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import flink.java.opentsdb.client.builder.MetricBuilder;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created on 2019-06-17
 *
 * @author :hao.li
 */
public class SinkToOpentsdb extends RichSinkFunction<String> {
    private Logger logger = LoggerFactory.getLogger(SinkToOpentsdb.class);
    HttpClientImpl client;

    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        client = new HttpClientImpl("http://10.3.6.12:4242");

    }

    public void close() throws Exception {
        super.close();
        if (client != null) {
            client.close();
        }
    }

    public void invoke(String value, Context context) throws Exception {
        ObjectMapper om = new ObjectMapper();
        Struct personBean = om.readValue(value, Struct.class);
        MetricBuilder builder = MetricBuilder.getInstance();
        builder.addMetric(personBean.getName()).setDataPoint(personBean.getAge())
                .addTag("tag1", "tab1value");
        client.pushMetrics(builder);
    }


}
