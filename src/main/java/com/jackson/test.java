package com.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/12/13  10:21
 */
public class test {
    public static void main(String[] args) {
        String jsonstr = "{\"msg\":{\"head\":{\"version\":\"1.0\",\"bizcode\":\"1006\",\"senddate\":\"20140827\",\"sendtime\":\"110325\",\"seqid\":\"1\"},\"body\":{\"datalist\":\"wahaha\",\"rstcode\":\"000000\",\"rstmsg\":\"成功\"}}}";
        ObjectMapper mapper = new ObjectMapper();

        //允许出现特殊字符和转义符
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        JsonNode root = null;
        try {
            root = mapper.readTree(jsonstr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //path与get作用相同,但是当找不到该节点的时候,返回missing node而不是Null.
        JsonNode msg = root.get("msg");
        JsonNode head = msg.get("head");
        JsonNode body = msg.get("body");

        JsonNode bizcode1 = head.path("bizcode1");
        if(bizcode1.size()==0){
            System.out.println("bizcode1 is null");
        }
        String datalist = body.path("datalist1").asText();
//        JsonNode bizcode1 = head.get("bizcode1");
//        String datalist1 = body.get("datalist1").asText();

//        System.err.println(bizcode);
        System.err.println(datalist);
//        System.out.println(bizcode1);

        System.err.println(root.path("msg").path("body").path("datalist").asText());
    }
}
