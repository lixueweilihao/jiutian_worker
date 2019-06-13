package com.play.kafka;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/15  18:30
 */
public class test5 {
    public static int[] twoSum(int[] nums, int target) {
        int[] b = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    b[0] = i;
                    b[1] = j;
                }
            }
        }
        return b;
    }


    public static void main(String[] args) {
        int[] a = new int[4];
        a[0] = 2;
        a[1] = 2;
        a[2] = 11;
        a[3] = 15;
        int target = 4;
        int[] ints = twoSum(a, target);
        System.out.println(ints[0]+": "+ints[1]);
//       Map<String,String> map = new HashMap<>();
//       map.put("lihaoa","b");
//       map.put("lihaob","b");
//       map.put("dddd","b");
//        Set<String> lihao = map.keySet().stream().filter(e -> e.startsWith("lihao")).collect(Collectors.toSet());
//        System.out.println(lihao);

//        String a = map.remove("a");
//        System.out.println(a);
    }
}
