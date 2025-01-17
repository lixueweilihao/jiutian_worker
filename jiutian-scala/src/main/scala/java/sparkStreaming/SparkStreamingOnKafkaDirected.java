//package java.sparkStreaming;
//
//import kafka.serializer.StringDecoder;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.api.java.function.Function2;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.streaming.Durations;
//import org.apache.spark.streaming.api.java.JavaDStream;
//import org.apache.spark.streaming.api.java.JavaPairDStream;
//import org.apache.spark.streaming.api.java.JavaPairInputDStream;
//import org.apache.spark.streaming.api.java.JavaStreamingContext;
//import org.apache.spark.streaming.kafka.KafkaUtils;
//import scala.Tuple2;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//
//
///**
// * Copyright @ 2018
// * All right reserved.
// *
// * @author Li Hao
// * @since 2018/12/28  17:49
// */
//public class SparkStreamingOnKafkaDirected {
//    public static void main(String[] args) {
//
//        SparkConf conf = new SparkConf().setMaster("spark://:7077").setAppName("SparkStreamingOnKafkaDirected");
//        JavaStreamingContext jsc = new JavaStreamingContext(conf, Durations.seconds(10));
//
//        Map<String, String> kafkaParameters = new HashMap<String, String>();
//        kafkaParameters.put("metadata.broker.list", "kafkasit02broker01..com:9092,kafkasit02broker02..com:9092,kafkasit02broker03..com:9092");
//
//        HashSet<String> topics = new HashSet<String>();
//        topics.add("dataflow");
//        JavaPairInputDStream<String, String> lines = KafkaUtils.createDirectStream(jsc,
//                String.class,
//                String.class,
//                StringDecoder.class,
//                StringDecoder.class,
//                kafkaParameters,
//                topics);
//
////        JavaDStream<String> words = lines.flatMap(new FlatMapFunction<Tuple2<String, String>, String>() {
////            //如果是Scala，由于SAM转换，所以可以写成val words = lines.flatMap { line => line.split(" ")}
////
////
////            private static final long serialVersionUID = 1L;
////
////            public Iterable<String> call(Tuple2<String, String> tuple) throws Exception {
////                return Arrays.asList(tuple._2.split("\t"));
////            }
////        });
//        JavaDStream<String> words = lines.map(
//                new Function<Tuple2<String, String>, String>() {
//                    public String call(Tuple2<String, String> v1) throws Exception {
//                        return v1._2();
//                    }
//                });
//
//        JavaPairDStream<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
//            private static final long serialVersionUID = 1L;
//
//            public Tuple2<String, Integer> call(String word) throws Exception {
//                return new Tuple2<String, Integer>(word, 1);
//            }
//        });
//        JavaPairDStream<String, Integer> wordsCount = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() { //对相同的Key，进行Value的累计（包括Local和Reducer级别同时Reduce）
//
//
//            private static final long serialVersionUID = 1L;
//
//            public Integer call(Integer v1, Integer v2) throws Exception {
//                return v1 + v2;
//            }
//        });
//        wordsCount.print();
//        jsc.start();
//        try {
//            jsc.awaitTermination();
//            jsc.close();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//}
