package com.play.gun;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;


public class MapsTest {

    public static void main(String[] args) {
//        TaskUnit tu = new TaskUnit("442", "", "10.37.167.202-7180");
//        MapsTest mt = new MapsTest();
//        Set<TaskUnit> abc = newHashSet();
//        abc.add(tu);
//        abc.stream().filter(a->a.getJobId().)
//        Map<String, Map<String, Set<String>>> mapNew = abc.stream().collect(Collectors.groupingBy(TaskUnit::getWorker,
//                Collectors.groupingBy(TaskUnit::getJobId,
//                        Collectors.mapping(TaskUnit::getPartition, Collectors.toSet()))));
////        System.out.println(mapNew.values());
//        mt.getMetch(mapNew);


//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        String collect = list.stream().collect(Collectors.joining("\n"));
//        System.out.println(collect);
        Map<String, String> map = new HashMap<String, String>();
        map.put("3", "c");
        map.put("1", "a");
        map.put("2", "b");
        map.put("4", "t");
        map.put("7", "0");
//       if(map.getOrDefault("1",null)=="b"){
//           System.out.println("True");
//       }else {
//           System.out.println("False");
//       }
//        String key = map.get("8");
//        if (key == null) {
//            key = new String();
//            map.put("key", key);
//        }
//        System.out.println(map);
//        map.computeIfAbsent("7",)
//        map.entrySet().removeIf(e->e.getValue().equals("0"));
//        map.entrySet().stream().filter(a-> a.)

//        map.entrySet().stream().filter(entry->entry.getValue().equals("0")).
//        System.out.println("========原生Map=======");
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
//
        Map<String, String> guavaMap = Maps.newHashMap();
        guavaMap.put("3", "c");
        guavaMap.put("1", "a");
        guavaMap.put("2", "b");
        guavaMap.put("5", "t");
        guavaMap.put("7", "D");
//        System.out.println(guavaMap);
//        System.out.println("========Guava Map=======");
//        for (Map.Entry<String, String> entry : guavaMap.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
//
        MapDifference<String, String> diffMap = Maps.difference(map, guavaMap);
        System.out.println("========Guava diff Map=======");
//        for (Map.Entry<String, String> entry : diffMap.entriesInCommon().entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
        Map<String, String> stringStringMap = diffMap.entriesOnlyOnLeft();
        System.out.println(stringStringMap);
        Map<String, String> stringStringMap1 = diffMap.entriesOnlyOnRight();
        System.out.println(stringStringMap1);
        Map<String, MapDifference.ValueDifference<String>> stringValueDifferenceMap = diffMap.entriesDiffering();
       stringValueDifferenceMap.values().stream().forEach(e-> System.out.println(e.rightValue()));
        Map<String, String> stringStringMap2 = diffMap.entriesInCommon();
        System.out.println(stringStringMap2);
        Map<String, MapDifference.ValueDifference<String>> stringValueDifferenceMap1 = diffMap.entriesDiffering();


        System.out.println("========Guava diff Left Map=======");
        for (Map.Entry<String, String> entry : diffMap.entriesOnlyOnLeft().entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
//        System.out.println("========Guava diff Right Map=======");
//        for (Map.Entry<String, String> entry : diffMap.entriesOnlyOnRight().entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
//        System.out.println("========Guava entriesDiffering() Map=======");
//        for (Map.Entry<String, MapDifference.ValueDifference<String>> entry : diffMap.entriesDiffering().entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }

//        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
//        Map<String, Integer> right = ImmutableMap.of("a", 1, "b", 2, "c", 3);
//        MapDifference<String, Integer> diff = Maps.difference(left, right);
//        Map<String,Integer> map2 = diff.entriesInCommon();
//        System.out.println("========Guava diff Map=======");
//        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }

    }
    public void getMetch( Map<String, Map<String, Set<String>>> mapNew){
//        processChangedEntries(mapNew,this::deleteTaskNode);
        processChangedEntries(mapNew, workerEntry ->overrideTaskNode(workerEntry));
    }
    public void processChangedEntries(Map<String, Map<String, Set<String>>> deltaMapping,
                                             Consumer<Map.Entry<String, Map<String, Set<String>>>> consumer) {
        deltaMapping.entrySet()
                .stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .forEach(consumer);
    }

    private void deleteTaskNode(Map.Entry<String, Map<String, Set<String>>> workerEntry) {
        workerEntry.getValue()
                .entrySet()
                .stream()
                .filter(jobSubEntry -> jobSubEntry.getValue().isEmpty())
                .forEach(jobSubEntry -> System.out.println(jobSubEntry.getValue()));
    }

    private void overrideTaskNode(Map.Entry<String, Map<String, Set<String>>> workerEntry) {
        workerEntry.getValue()
                .entrySet()
                .stream()
                .filter(jobSubEntry -> !jobSubEntry.getValue().isEmpty())
                .forEach(jobSubEntry -> {
                    String jobId = jobSubEntry.getKey();
                    String workerId = workerEntry.getKey();
                    Set<String> partitions = jobSubEntry.getValue();
                });
    }

}
