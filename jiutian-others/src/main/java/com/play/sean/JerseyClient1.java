package com.play.sean;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import javax.ws.rs.core.MediaType;
import java.util.HashSet;

public class JerseyClient1 {

    public static void main(String[] args) {
        HashSet<String> hs = new HashSet();
        ClientConfig cc = new DefaultClientConfig();
        //使用Jersey对POJO的支持，必须设置为true
        cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(cc);

        WebResource resource = client.resource("http://127.0.0.1:10000/query");

        Request req = new Request();
        req.setQuery("name");

        ClientResponse response = resource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, req);

        Response resp = response.getEntity(Response.class);
        System.out.println(resp.getRespCode() + " " + resp.getRespDesc());
    }
}
