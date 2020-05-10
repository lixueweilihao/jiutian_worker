package atguigu.demo;

import com.dtc.java.analytic.V2.common.model.AlterStruct;
import com.dtc.java.analytic.V2.common.model.DataStruct;
import com.dtc.java.analytic.V2.common.model.SourceEvent;
import com.dtc.java.analytic.V2.common.utils.ExecutionEnvUtil;
import com.dtc.java.analytic.V2.common.utils.KafkaConfigUtil;
import com.dtc.java.analytic.V2.common.utils.Localhost;
import com.dtc.java.analytic.V2.map.function.LinuxMapFunction;
import com.dtc.java.analytic.V2.map.function.WinMapFunction;
import com.dtc.java.analytic.V2.process.function.LinuxProcessMapFunction;
import com.dtc.java.analytic.V2.process.function.WinProcessMapFunction;
import com.dtc.java.analytic.V2.sink.mysql.MysqlSink;
import com.dtc.java.analytic.V2.sink.opentsdb.PSinkToOpentsdb;
import com.dtc.java.analytic.V2.source.mysql.ReadAlarmMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple9;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.nfa.aftermatch.AfterMatchSkipStrategy;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Created on 2019-08-12
 *
 * @author :ren
 */
@Slf4j
public class StreamToFlinkV3 {
    final static MapStateDescriptor<String, String> ALARM_RULES = new MapStateDescriptor<>(
            "alarm_rules",
            BasicTypeInfo.STRING_TYPE_INFO,
            BasicTypeInfo.STRING_TYPE_INFO);
    private static DataStream<Map<String, String>> alarmDataStream = null;

    public static void main(String[] args) throws Exception {

        final ParameterTool parameterTool = ExecutionEnvUtil.createParameterTool(args);
        String opentsdb_url = parameterTool.get("dtc.opentsdb.url", "http://10.10.58.16:4399");
        Map<String, String> stringStringMap = parameterTool.toMap();
        Properties properties = new Properties();
        for (String key : stringStringMap.keySet()) {
            if (key.startsWith("mysql")) {
                properties.setProperty(key, stringStringMap.get(key));
            }
        }
        int windowSizeMillis = parameterTool.getInt("dtc.windowSizeMillis", 2000);
        StreamExecutionEnvironment env = ExecutionEnvUtil.prepare(parameterTool);
        env.getConfig().setGlobalJobParameters(parameterTool);
        DataStreamSource<Tuple9<String, String, String, String, Double, String, String, String, String>> alarmMessageMysql = env.addSource(new ReadAlarmMessage()).setParallelism(1);
        DataStream<Map<String, Tuple9<String, String, String, Double, Double, Double, Double, String, String>>> process = alarmMessageMysql.keyBy(0, 5).timeWindow(Time.milliseconds(windowSizeMillis)).process(new MySqlProcessMapFunction());
        alarmDataStream = process.map(new MySQLFunction());

//        DataStreamSource<SourceEvent> streamSource = env.addSource(new TestSourceEvent());
        DataStreamSource<SourceEvent> streamSource = KafkaConfigUtil.buildSource(env);

        /**
         * {"time":"1581691002687","code":"101_101_107_105_105","host":"10.3.7.234","nameCN":"磁盘剩余大小","value":"217802544","nameEN":"disk_free"}
         * */
//        DataStreamSource<String> dataStreamSource = env.socketTextStream("172.20.10.2", 8080, '\n');

        SingleOutputStreamOperator<DataStruct> mapStream = streamSource.map(new MyMapFunctionV3());
//        SingleOutputStreamOperator<DataStruct> timeSingleOutputStream
//                = mapStream.assignTimestampsAndWatermarks(new DtcPeriodicAssigner());


        env.execute("Dtc-Alarm-Flink-Process");
    }
}



@Slf4j
class MyMapFunctionV3 implements MapFunction<SourceEvent, DataStruct> {
    @Override
    public DataStruct map(SourceEvent sourceEvent) {
        String[] codes = sourceEvent.getCode().split("_");
        String systemName = codes[0].trim() + "_" + codes[1].trim();
        String zbFourCode = systemName + "_" + codes[2].trim() + "_" + codes[3].trim();
        String zbLastCode = codes[4].trim();
        String nameCN = sourceEvent.getNameCN();
        String nameEN = sourceEvent.getNameEN();
        String time = sourceEvent.getTime();
        String value = sourceEvent.getValue();
        String host = sourceEvent.getHost();
        return new DataStruct(systemName, host, zbFourCode, zbLastCode, nameCN, nameEN, time, value);
    }
}

