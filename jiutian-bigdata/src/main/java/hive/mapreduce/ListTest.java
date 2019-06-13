package hive.mapreduce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihao on 2017/7/19.
 */
public class ListTest {
    public static void main(String[] args) {
        Map<String,String> map1 = new HashMap<String, String>();
        map1.put("1","a");
        map1.put("1","b");
        map1.put("1","c");
        Map<String,String> map2 = new HashMap<String, String>();
        map2.put("1","b");
        map2.put("2","b");
        map2.put("3","c");
        ArrayList<Map<String,String>> a1 = new ArrayList<Map<String,String>>();
        a1.add(map1);
        System.out.println("原集合："+a1);
        ArrayList<Map<String,String>> a2 = new ArrayList<Map<String,String>>();
        a2.add(map2);
        System.out.println("原集合2："+a2);


    }
}
