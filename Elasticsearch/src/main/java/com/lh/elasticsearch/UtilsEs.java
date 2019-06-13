package com.lh.elasticsearch;//package com.demo.es;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class UtilsEs {

    public static TransportClient getEsClient(String clusterName,String hosts,int PORT) {
        TransportClient client =null;
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", clusterName)
                    .build();
           client = new PreBuiltTransportClient(settings);
            TransportAddress transportAddress;
            for (String host : hosts.split(",")) {
                transportAddress = new TransportAddress(InetAddress.getByName(host), PORT);
                client.addTransportAddresses(transportAddress);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }
}
