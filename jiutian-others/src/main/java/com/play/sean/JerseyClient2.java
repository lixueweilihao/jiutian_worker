package com.play.sean;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.core.MediaType;

public class JerseyClient2 {

    public static void main(String[] args) {
        ClientConfig cc = new DefaultClientConfig();
        Client client = Client.create(cc);

        WebResource resource = client.resource("http://127.0.0.1:10000/query");

        JSONObject req = new JSONObject();
        try {
            req.put("query", "name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ClientResponse response = resource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, req);

        JSONObject resp = response.getEntity(JSONObject.class);
        //{"respCode":0,"respDesc":"name"}
        System.out.println(resp.toString());
    }
}

