package atguigu.demo1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.IOException;

public class DtcPeriodicAssigner implements AssignerWithPeriodicWatermarks<DataStruct> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DtcPeriodicAssigner.class);

    private long maxOutOfOrderness = 2000; // 2 seconds

    private long currentMaxTimestamp = 0L;


    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        Watermark watermark = new Watermark(currentMaxTimestamp - maxOutOfOrderness);
        return watermark;

    }

    @Override
    public long extractTimestamp(DataStruct s, long l) {
        String time1 = s.getTime();
        long timestamp = Long.parseLong(time1);
        return timestamp;
    }
}
