package com.play.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lihao
 * @Date: Create in 14:23 2017/12/14
 * @Description:
 * @Modified By:
 */
public class TestList {
    public Map<String, Integer> getMap(List<String> list1, List<String> list2) {
        Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());
        List<String> maxList = list1;
        List<String> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }
        maxList.forEach(k -> map.put(k, 1));
        minList.forEach(k -> {
            Integer cc = map.get(k);
            if (cc != null) {
                map.put(k, ++cc);
            } else {
                map.put(k, 1);
            }
        });

//        for (String string : minList) {
//            Integer cc = map.get(string);
//            if (cc != null) {
//                map.put(string, ++cc);
//                continue;
//            }
//            map.put(string, 1);
//        }
        return map;
    }

    public static void main(String[] args) {
        TestList tl = new TestList();
        List one = new ArrayList();
        List two = new ArrayList();
        one.add("1");
        one.add("2");
        one.add("3");
        one.add("4");
        one.add("5");
        one.add("6");
        two.add("2");
        two.add("2");
        two.add("3");
        two.add("4");
        Map<String, Integer> mapone = tl.getMap(one, two);
        System.out.println(mapone);
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
//        list.forEach( str -> {
//            if(str.length()>3)
//                System.out.println(str);
//        });
    }
}
