package lihao;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;
import storm.kafka.bolt.KafkaBolt;

import java.util.HashMap;
import java.util.Map;

public class StormKafkaTopo {
    public static void main(String[] args) {
        // 注册 ZooKeeper 主机
        BrokerHosts brokerHosts = new ZkHosts("10.37.167.204:2181,10.37.167.203:2181,10.37.167.202:2181/kafka");
        // 配置 Spout
        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "k2es", "/kafka", "kafkaspout");

        Config conf = new Config();
        Map<String, String> map = new HashMap<String, String>();

        map.put("metadata.broker.list", "10.37.167.204:9092,10.37.167.203:9092,10.37.167.202:9092");
        map.put("serializer.class", "kafka.serializer.StringEncoder");
        conf.put("kafka.broker.properties", map);
        conf.put("topic", "k2h_avro");

        spoutConfig.scheme = new SchemeAsMultiScheme(new MessageScheme());//StringScheme告诉KafkaSpout如何去解码数据，生成Storm内部传递数据

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new KafkaSpout(spoutConfig));
        builder.setBolt("bolt", new SenqueceBolt()).shuffleGrouping("spout");
        builder.setBolt("kafkabolt", new KafkaBolt<String, Integer>()).shuffleGrouping("bolt");

        if (args != null && args.length > 0) {
            //提交到集群运行
            try {
                StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            }
        } else {
            //本地模式运行
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("Topotest1121", conf, builder.createTopology());
            Utils.sleep(1000000);
            cluster.killTopology("Topotest1121");
            cluster.shutdown();
        }


    }
}
