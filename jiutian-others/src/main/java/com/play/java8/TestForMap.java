package com.play.java8;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @Author: lihao
 * @Date: Create in 9:57 2017/12/14
 * @Description:
 * @Modified By:
 */
public class TestForMap {
    public Map<String, String> changeStatus(Map<String, Integer> map, Integer num, String str1, String str2) {
        Map<String, String> mapTwo = new HashMap<>();
        for (String str : map.keySet()) {
            if (map.get(str) > 10) {
                mapTwo.put(str, str1);
            } else {
                mapTwo.put(str, str2);
            }
        }
        return mapTwo;
    }

    public List<String> getSame(Map<String, Integer> map) {
        List<String> same = new ArrayList<String>();
        map.forEach((k, v) -> {
            if (v != 1) {
                same.add(k);
            }
        });
        return same;
    }

    public static void main(String[] args) {
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);

        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        System.out.println("Original...");
        System.out.println(unsortMap.get("e"));

        // sort by keys, a,b,c..., and return a new LinkedHashMap
        // toMap() will returns HashMap by default, we need LinkedHashMap to keep the order.
        Map<String, Integer> result = unsortMap.entrySet().stream()
                .sorted(Entry.comparingByKey())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
//        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println(result);
//        TestForMap tfm = new TestForMap();
//        Map<String, String> mapTwo = new HashMap<>();
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        map.put("a", 10);
//        map.put("b", 15);
//        map.put("c", 25);
//        map.put("d", 20);
//        List list = map.entrySet().stream().filter(key -> key.getKey().equals("a")).map(key ->key.getValue()).collect(Collectors.toList());
//        System.out.println(list);
        // System.out.println(tfm.getSame(map));
//        System.out.println(map.getOrDefault("e", 0));

        //第一种遍历方式
//        int sum = 0;
//        for (Integer num : map.values()) {
//            sum += num;
//        }
//        第二种遍历方式
//        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
//        while(it.hasNext()){
//            sum+=it.next().getValue();
//        }
//        第三种遍历方式
//        for(Map.Entry<String,Integer> str : map.entrySet()){
//            sum+=str.getValue();
//        }
//        第四种遍历方式
//          for(String str : map.keySet()){
//              sum+=map.get(str);
//          }
//        System.out.println(sum);
    }
}
