package com.hotitmes;

/**
 * @Author : lihao
 * Created on : 2020-03-23
 * @Description : TODO描述类作用
 */
import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * 商场实时数据统计,基于Flink 1.9版本
 */
public class MarketStreamCount {
    public static void main(String[] args) {


        // todo 1，读取kafka数据
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment(8);
        //todo 获取kafka的配置属性
        args = new String[]{"--input-topic", "user_behavior", "--bootstrap.servers", "node2.hadoop:9091,node3.hadoop:9091",
                "--zookeeper.connect", "node1.hadoop:2181,node2.hadoop:2181,node3.hadoop:2181", "--group.id", "cc2"};

        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        Properties pros = parameterTool.getProperties();
//        //todo 指定输入数据为kafka topic
        DataStream<String> kafkaDstream = env.addSource(new FlinkKafkaConsumer<String>(
                pros.getProperty("input-topic"),
                new SimpleStringSchema(),
                pros).setStartFromEarliest()

        ).setParallelism(1);

        //todo 2,每层实时顾客人数，实时顾客总数，一天的实时顾客总数

        // 转成json
        DataStream<JSONObject> kafkaDstream2 = kafkaDstream.map(new MapFunction<String, JSONObject>() {
            @Override
            public JSONObject map(String input) throws Exception {
                JSONObject inputJson = null;
                try {
                    inputJson = JSONObject.parseObject(input);
                    return inputJson;
                } catch (Exception e) {
                    e.printStackTrace();
//                    return null;
                }
                return inputJson;
            }
        }).filter(new FilterFunction<JSONObject>() {
            @Override
            public boolean filter(JSONObject jsonObject) throws Exception {
                if (jsonObject!=null){
                    return true;
                }
                return false;
            }
        });
        //给数据加上了一个水印
        DataStream<JSONObject> timedData = kafkaDstream2
                .assignTimestampsAndWatermarks(new AscendingTimestampExtractor<JSONObject>() {
                    @Override
                    public long extractAscendingTimestamp(JSONObject json) {
                        // 原始数据单位秒 ，将其转成毫秒
                        return json.getLong("category_id") * 1000;
                    }
                });

        //过滤出点击事件
        // 实时客人数，各个层级
        DataStream<JSONObject> windowedData = timedData.keyBy(new KeySelector<JSONObject, String>() {
            @Override
            public String getKey(JSONObject jsonObject) throws Exception {
                return  jsonObject.getString("user_id");
            }
        }).timeWindow(Time.seconds(5L),Time.seconds(1L))
                //使用aggregate做增量聚合统计
                .aggregate(new CountAgg(),new WindowResultFunction());



        windowedData.print();
        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //todo 功能是统计窗口中的条数，即遇到一条数据就加一

    public static class CountAgg implements AggregateFunction<JSONObject, Long, Long> {

        @Override
        public Long createAccumulator() { //创建一个数据统计的容器，提供给后续操作使用。
            return 0L;
        }

        @Override
        public Long add(JSONObject json, Long acc) { //每个元素被添加进窗口的时候调用。
            return acc + 1;
        }

        @Override
        public Long getResult(Long acc) {
            //窗口统计事件触发时调用来返回出统计的结果。
            return acc;
        }

        @Override
        public Long merge(Long acc1, Long acc2) { //只有在当窗口合并的时候调用,合并2个容器
            return acc1 + acc2;
        }
    }

    // todo 指定格式输出
    public static class WindowResultFunction implements WindowFunction<Long, JSONObject, String, TimeWindow> {

        @Override
        public void apply(
                String key,  // 窗口的主键，即 itemId
                TimeWindow window,  // 窗口
                Iterable<Long> aggregateResult, // 聚合函数的结果，即 count 值
                Collector<JSONObject> collector  // 输出类型为 ItemViewCount
        ) throws Exception {

            Long count = aggregateResult.iterator().next();
            //窗口结束时间
            long end = window.getEnd();
            JSONObject json = new JSONObject();
            json.put("key",key);
            json.put("count",count);
            json.put("end",end);
            collector.collect(json);
        }
    }

}
