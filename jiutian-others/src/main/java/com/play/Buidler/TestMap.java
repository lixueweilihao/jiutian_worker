package com.play.Buidler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestMap {
    public void getList(String name){
        Map<String, Long> map = new HashMap<>();
        map.put("lihao", 5L);
        map.put("lio", 6L);
        System.out.println(map);
        List list = map.entrySet().stream().filter(entry -> entry.getKey().equals(name)).collect(Collectors.toList());
        System.out.println(list);
    }
    public static void main(String[] args) {
        TestMap   tm = new TestMap();
        tm.getList("lihao");
////        map.entrySet().stream().filter(entry -> entry.getKey().equals("lihao")).collect(Collectors.toList());
//        List list = map.entrySet().stream().filter(entry -> entry.getKey().equals("lihao")).collect(Collectors.toList());
//        System.out.println(list);
    }
}
