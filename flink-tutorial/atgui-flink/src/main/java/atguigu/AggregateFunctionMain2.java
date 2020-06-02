package atguigu;

/**
 * @Author : lihao
 * Created on : 2020-04-22
 * @Description : TODO描述类作用
 */


import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * @author: Created By lujisen
 * @company ChinaUnicom Software JiNan
 * @date: 2020-04-14 11:28
 * @version: v1.0
 * @description: com.hadoop.ljs.flink110.aggreagate
 * 自定义聚合函数类和窗口类，进行商品访问量的统计，根据滑动时间窗口，按照访问量排序输出
 */
public class AggregateFunctionMain2 {

    public  static int windowSize=6000;/*滑动窗口大小*/
    public  static int windowSlider=3000;/*滑动窗口滑动间隔*/

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        senv.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        /*DataStream<String> sourceData = senv.socketTextStream("localhost",9000);*/
        //从文件读取数据，也可以从socket读取数据
        DataStream<String> sourceData = senv.readTextFile("/Users/lixuewei/workspace/private/jiutian_worker/flink-tutorial/atgui-flink/src/main/resources/newData.txt");
        DataStream<ProductViewData> productViewData = sourceData.map(new MapFunction<String, ProductViewData>() {
            @Override
            public ProductViewData map(String value) throws Exception {
                String[] record = value.split(",");
                return new ProductViewData(record[0], record[1], Long.valueOf(record[2]), Long.valueOf(record[3]));
            }
        }).assignTimestampsAndWatermarks(new AscendingTimestampExtractor<ProductViewData>(){
            @Override
            public long extractAscendingTimestamp(ProductViewData element) {
                return element.timestamp;
            }
        });
        /*过滤操作类型为1  点击查看的操作*/
        DataStream<String> productViewCount = productViewData
//                .filter(new FilterFunction<ProductViewData>() {
//            @Override
//            public boolean filter(ProductViewData value) throws Exception {
//                if(value.operationType==1){
//                    return true;
//                }
//                return false;
//            }
//        })
                .keyBy(new KeySelector<ProductViewData, String>() {
            @Override
            public String getKey(ProductViewData value) throws Exception {
                return value.productId;
            }
            //时间窗口 6秒  滑动间隔3秒
        }).timeWindow(Time.seconds(3))
                /*这里按照窗口进行聚合*/
                .aggregate(new MyCountAggregate(), new MyCountWindowFunction2());
        //聚合结果输出
        productViewCount.print();

        senv.execute("AggregateFunctionMain2");
    }
}
class MyCountWindowFunction2 implements WindowFunction<Long,String,String, TimeWindow> {
    @Override
    public void apply(String productId, TimeWindow window, Iterable<Long> input, Collector<String> out) throws Exception {
        /*商品访问统计输出*/
        /*out.collect("productId"productId,window.getEnd(),input.iterator().next()));*/
        out.collect("----------------窗口时间："+window.getEnd());
        out.collect("商品ID: "+productId+"  浏览量: "+input.iterator().next());
    }
}
class MyCountAggregate implements AggregateFunction<ProductViewData, Tuple2<Long,Long>, Long> {
    @Override
    public Tuple2 createAccumulator() {
        /*访问量初始化为0*/
        return Tuple2.of(0L,0L);
    }
    @Override
    public Tuple2<Long, Long> add(ProductViewData value, Tuple2<Long, Long> acc) {
        /*访问量直接+1 即可*/
        return new Tuple2<Long,Long>(acc.f0 + value.operationType, acc.f1 + 1L);
    }
    @Override
    public Long getResult(Tuple2<Long, Long> acc) {
        return acc.f0;
    }

    @Override
    public Tuple2<Long, Long> merge(Tuple2<Long, Long> longLongTuple2, Tuple2<Long, Long> acc1) {
        return new Tuple2<>(longLongTuple2.f0 + acc1.f0, longLongTuple2.f1 + acc1.f1);
    }
}



