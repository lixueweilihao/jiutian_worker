package com.play.java8;

import java.util.*;
import java.util.stream.Collectors;

class Tree {
    String name;
    static int age;

    public Tree(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class Test {
    static Tree a = new Tree("lisi", 20);
    static Tree b = new Tree("zhangsan", 30);
    static Tree c = new Tree("wangwu", 40);
    static Tree f = new Tree("wangwu", 50);
    static Tree d = new Tree("lisi", 40);
    static Tree e = new Tree("lisi", 50);

    public Integer getSum(Map<Tree, Integer> map) {
//        return map.entrySet().stream().filter(tree -> tree.getKey().getName().equals("lisi")).mapToInt().;
        return null;
    }

    public Map<Integer, Integer> getNameOne(Map<Tree, Integer> map, String name) {
        return map.entrySet().stream().filter(task -> task.getKey().getName().equals(name)).
                collect(Collectors.toMap(task -> task.getKey().getAge(), task -> task.getValue()));
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("lisi");
        list.add("zhangsan");
        list.add("wangwu");
        Test test = new Test();
//        List<String> abc = new ArrayList<>();
        Map<Tree, Integer> mapone = new HashMap<>();
        mapone.put(a, 1);
        mapone.put(b, 1);
        mapone.put(c, 3);
        mapone.put(d, 4);
        mapone.put(e, 5);
        mapone.put(f, 6);
        Map<String,Long> mapOfOne = mapone.entrySet().stream().collect(Collectors.
                groupingBy(entry -> entry.getKey().getName(), Collectors.counting()));
        System.out.println(mapOfOne);

        Map<String,Set<Tree>> mapOfTwo = mapone.entrySet().stream().collect(Collectors.
                groupingBy(entry -> {
                    return entry.getKey().getName();
                },Collectors.mapping(entry -> entry.getKey(),Collectors.toSet())));
        System.out.println(mapOfTwo);
        //        mapone.entrySet().stream().collect(Collectors.groupingBy(entry -> entry.getKey().getName(), Collectors.counting()));
//       Integer list1 = mapone.entrySet().stream().filter(key -> key.getKey().getName().equals("lisi")).mapToInt(key ->key.getValue()).sum();
//        System.out.println(list1);
//
// Map<String, Integer> maptwo = new HashMap<>();;
//        for (int i = 0; i < list.size(); i++) {
//            int sum =0;
//            for (Map.Entry<Tree, Integer> map : mapone.entrySet()) {
//                if (list.get(i).equals(map.getKey().getName())) {
//                   sum += map.getValue();
//                    maptwo.put(list.get(i),sum);
//                }
//            }
//        }
//        System.out.println("maptwo"+maptwo);
//        Map mapTwo = test.getNameOne(mapone, "lisi");
//        System.out.println(mapTwo);
//        int a = test.getSum(mapone);
//        System.out.println(a);
//        Map<Tree, String> maptwo = new HashMap<>();
//        Iterator<Map.Entry<Tree, Integer>> entries = mapone.entrySet().iterator();
//        while (entries.hasNext()) {
//            Tree key = entries.next().getKey();
//            System.out.println(key.getName() + "  " + key.getAge());
//            maptwo.put(key,key.getName());
    }
//        Iterator<Map.Entry<Tree, String>> entriestwo = maptwo.entrySet().iterator();
//        while (entriestwo.hasNext()) {
//            Tree key = entriestwo.next().getKey();
//            abc.add(key.getName());
//        }
//        for (int i = 0; i < abc.size(); i++) {
//            int count = 0;
//            for (int j = 0; j < abc.size(); j++) {
//                if (abc.get(i).equals(abc.get(j))) {
//                   count++;
//                }
//            }
//            if(count==1) {
//                for(Map.Entry<Tree, String> entry : maptwo.entrySet()){
//                    if(abc.get(i).equals(entry.getValue())){
//                        Tree key = entry.getKey();
//                        System.out.println(key.getName());
//                        mapone.remove(key);
//                    }
//                }
//
//            }
////                mapone.remove()
//        }
}
//}
