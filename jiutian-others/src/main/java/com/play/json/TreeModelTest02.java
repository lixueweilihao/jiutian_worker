package com.play.json;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TreeModelTest02 {
    private String jsonString =
            "{\"errorMessage\":\"\",\"result\":[{\"createTime\":\"2018-05-31 17:23:25\",\"lag\":285,\"lastSeenTime\":\"2018-05-31 17:24:43\",\"logSize\":362,\"offset\":77,\"owner\":\"\",\"partition\":\"0\",\"replicas\":\"\",\"topicName\":\"aaatopic\"},{\"createTime\":\"2018-05-31 17:23:25\",\"lag\":200,\"lastSeenTime\":\"2018-05-31 17:24:43\",\"logSize\":377,\"offset\":177,\"owner\":\"\",\"partition\":\"1\",\"replicas\":\"\",\"topicName\":\"aaatopic\"},{\"createTime\":null,\"lag\":485,\"lastSeenTime\":null,\"logSize\":739,\"offset\":254,\"owner\":\"\",\"partition\":\"total\",\"replicas\":\"\",\"topicName\":\"aaatopic\"}],\"success\":1,\"successData\":true}";

    public void testTreeModelParse() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //使用ObjectMapper的readValue方法将json字符串解析到JsonNode实例中
        JsonNode rootNode = objectMapper.readTree(jsonString);
        //直接从rootNode中获取某个键的值，
        //这种方式在如果我们只需要一个长json串中某个字段值时非常方便
        JsonNode nameNode = rootNode.get("errorMessage");
        String name = nameNode.asText();
        System.out.println(name);
        //从 rootNode 中获取数组节点
        JsonNode skillsNode = rootNode.get("skills");
        for (int i = 0; i < skillsNode.size(); i++) {
            System.out.println(skillsNode.get(i).asText());
        }
        //从 rootNode 中获取子对象节点
        JsonNode petsNode = rootNode.get("pets");
        String petsName = petsNode.get("petName").asText();
        System.out.println(petsName);
    }

    public static void main(String[] args) throws IOException {
        TreeModelTest02 tmt = new TreeModelTest02();
        tmt.testTreeModelParse();
    }
}
