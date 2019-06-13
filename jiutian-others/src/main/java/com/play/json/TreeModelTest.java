package com.play.json;//package json;
//
//
////import com.fasterxml.jackson.core.JsonFactory;
////import com.fasterxml.jackson.core.JsonGenerator;
////import com.fasterxml.jackson.databind.ObjectMapper;
////import com.fasterxml.jackson.databind.node.ArrayNode;
////import com.fasterxml.jackson.databind.node.JsonNodeFactory;
////import com.fasterxml.jackson.databind.node.ObjectNode;
//
//import java.io.IOException;
//
//public class TreeModelTest {
//    //JsonNodeFactory 实例，可全局共享
//    private JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
//    //JsonFactory 实例，线程安全
//    private JsonFactory jsonFactory = new JsonFactory();
//
//    public void testTreeModelGenerate() throws IOException {
//
//        //根节点
//        ObjectNode rootNode = jsonNodeFactory.objectNode();
//        //往根节点中添加普通键值对
//        rootNode.put("name", "aaa");
//        rootNode.put("email", "aaa@aa.com");
//        rootNode.put("age", 24);
//        //往根节点中添加一个子对象
//        ObjectNode petsNode = jsonNodeFactory.objectNode();
//        petsNode.put("petName", "kitty")
//                .put("petAge", 3);
//        rootNode.set("pets", petsNode);
//        //往根节点中添加一个数组
//        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
//        arrayNode.add("java")
//                .add("linux")
//                .add("mysql");
//        rootNode.set("skills", arrayNode);
//        //调用ObjectMapper的writeTree方法根据节点生成最终json字符串
//        JsonGenerator generator = jsonFactory.createGenerator(System.out);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeTree(generator, rootNode);
//    }
//
//    public static void main(String args[]) throws IOException {
//        TreeModelTest tmt = new  TreeModelTest();
//        tmt.testTreeModelGenerate();
//
//    }
//}
