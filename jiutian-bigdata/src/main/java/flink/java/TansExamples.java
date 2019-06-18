package flink.java;

import org.apache.flink.api.common.functions.*;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
/**
 * Created on 2019-06-17
 *
 * @author :hao.li
 */
public class TansExamples {
    public static void main(String[] args) throws Exception{
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> dataStream = env.fromElements("can you help me see can you help me you you");

        DataStream<String> result = dataStream.flatMap(new FlatMapFunction<String, WordWithCount>() {
            @Override
            public void flatMap(String value, Collector<WordWithCount> out) throws Exception {
                for(String word: value.split(" ")){
                    out.collect(new WordWithCount(word, 1));
                }
            }
        }).keyBy("word")
                .fold("", new FoldFunction<WordWithCount, String>() {
                    @Override
                    public String fold(String current, WordWithCount value) throws Exception {
                        if(current.equals("start")){
                            return current + "_" + value.word + "_" + value.count;
                        }
                        else{
                            return current + "_" + value.count;
                        }

                    }
                });
//        System.out.println(result.toString());
        result.print().setParallelism(1);
        env.execute("test for map");
    }
    public static class WordWithCount{
        public String word;
        public Integer count;
        public WordWithCount(){}
        public WordWithCount(String word, Integer count){
            this.word = word;
            this.count = count;
        }
        @Override
        public String toString(){
            return word + ":" + count;
        }
    }
}
