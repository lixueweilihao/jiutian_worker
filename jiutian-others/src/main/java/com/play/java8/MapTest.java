package com.play.java8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/12/6  17:38
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("a",1);
        map.put("b",2);
        System.out.println(map);
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            if("a".equals(next.getKey())){
                System.out.println(next.getValue());
                iterator.remove();
            }
        }
        System.out.println(map);

    }
}
