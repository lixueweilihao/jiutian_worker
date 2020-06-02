//package atguigu.demo1;
//
//
//import atguigu.ProductViewData;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.flink.api.common.functions.AggregateFunction;
//import org.apache.flink.api.common.functions.MapFunction;
//import org.apache.flink.api.java.functions.KeySelector;
//import org.apache.flink.api.java.tuple.Tuple2;
//import org.apache.flink.api.java.utils.ParameterTool;
//import org.apache.flink.streaming.api.datastream.DataStreamSource;
//import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
//import org.apache.flink.streaming.api.windowing.time.Time;
//import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
//import org.apache.flink.util.Collector;
//
///**
// * Created on 2019-08-12
// *
// * @author :ren
// */
//@Slf4j
//public class StreamToFlinkV3 {
//
//    public static void main(String[] args) throws Exception {
//
//        final ParameterTool parameterTool = ExecutionEnvUtil.createParameterTool(args);
//        int windowSizeMillis = parameterTool.getInt("dtc.windowSizeMillis", 2000);
//        StreamExecutionEnvironment env = ExecutionEnvUtil.prepare(parameterTool);
//        env.getConfig().setGlobalJobParameters(parameterTool);
//
//
////        DataStreamSource<SourceEvent> streamSource = env.addSource(new TestSourceEvent());
//        DataStreamSource<SourceEvent> streamSource = KafkaConfigUtil.buildSource(env);
//
//        /**
//         * {"time":"1581691002687","code":"101_101_107_105_105","host":"10.3.7.234","nameCN":"磁盘剩余大小","value":"217802544","nameEN":"disk_free"}
//         * */
////        DataStreamSource<String> dataStreamSource = env.socketTextStream("172.20.10.2", 8080, '\n');
//
//        SingleOutputStreamOperator<DataStruct> mapStream = streamSource.map(new MyMapFunctionV3());
//        SingleOutputStreamOperator<DataStruct> timeSingleOutputStream
//                = mapStream.assignTimestampsAndWatermarks(new DtcPeriodicAssigner());
//        timeSingleOutputStream.filter(e->e.getHost()!=null)
//                .keyBy(new KeySelector<DataStruct, String>() {
//            @Override
//            public String getKey(DataStruct value) throws Exception {
//                return value.getHost();
//            }
//            //时间窗口 6秒  滑动间隔3秒
//        })
//                .timeWindow(Time.seconds(60))
//                .aggregate(new CountAggregate(), new CountWindowFunction());
//
//
//        env.execute("Dtc-Alarm-Flink-Process");
//    }
//}
//
//class CountWindowFunction implements WindowFunction<Long, String, String, TimeWindow> {
//    @Override
//    public void apply(String productId, TimeWindow window, Iterable<Long> input, Collector<String> out) throws Exception {
//        /*商品访问统计输出*/
//        /*out.collect("productId"productId,window.getEnd(),input.iterator().next()));*/
//        out.collect("----------------窗口时间：" + window.getEnd());
//        out.collect("商品ID: " + productId + "  浏览量: " + input.iterator().next());
//    }
//}
//
//class CountAggregate implements AggregateFunction<DataStruct, Tuple2<String, Long>, Long> {
//    @Override
//    public Tuple2 createAccumulator() {
//        /*访问量初始化为0*/
//        return Tuple2.of("", 0L);
//    }
//
//    @Override
//    public Tuple2<String, Long> add(DataStruct value, Tuple2<String, Long> acc) {
//        /*访问量直接+1 即可*/
//        return new Tuple2<>(value.getHost() + ":" + value.getZbFourName(), Long.parseLong(acc.f1 + value.getValue()));
//    }
//
//    @Override
//    public Long getResult(Tuple2<String, Long> acc) {
//        return acc.f1;
//    }
//
//    @Override
//    public Tuple2<String, Long> merge(Tuple2<String, Long> longLongTuple2, Tuple2<String, Long> acc1) {
//        return new Tuple2<>(longLongTuple2.f0, longLongTuple2.f1 + acc1.f1);
//    }
//}
//
//
//@Slf4j
//class MyMapFunctionV3 implements MapFunction<SourceEvent, DataStruct> {
//    @Override
//    public DataStruct map(SourceEvent sourceEvent) {
//        String[] codes = sourceEvent.getCode().split("_");
//        String systemName = codes[0].trim() + "_" + codes[1].trim();
//        String zbFourCode = systemName + "_" + codes[2].trim() + "_" + codes[3].trim();
//        String zbLastCode = codes[4].trim();
//        String nameCN = sourceEvent.getNameCN();
//        String nameEN = sourceEvent.getNameEN();
//        String time = sourceEvent.getTime();
//        String value = sourceEvent.getValue();
//        String host = sourceEvent.getHost();
//        return new DataStruct(systemName, host, zbFourCode, zbLastCode, nameCN, nameEN, time, value);
//    }
//}
//
