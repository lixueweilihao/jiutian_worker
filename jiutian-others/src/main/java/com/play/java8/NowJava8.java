package com.play.java8;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/10/29  15:13
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NowJava8 {

    public static void main(String[] args) {

        List<String> lines = Arrays.asList("spring", "node", "mkyong");

//        List<String> result = lines.stream()                // convert list to stream
//                .filter(line -> !"mkyong".contains(line))     // we dont like mkyong
//                .collect(Collectors.toList());              // collect the output and convert streams to a List
//
//        result.forEach(System.out::println);                //output : spring, node
//         lines.stream().limit(1).forEach(e->System.out.println(e));
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");
        Set<String> collect = list2.stream().filter(e -> !list1.contains(e)).collect(Collectors.toSet());
        System.out.println(collect);
//        Map<String, Long> map = new HashMap<>();
//        map.put("1", 1L);
//        map.entrySet().stream().filter(e -> {
//            boolean equals = e.getValue().equals(1L);
//            return equals;
//        }).forEach(e -> System.out.print(e));
//    }

    }
}
