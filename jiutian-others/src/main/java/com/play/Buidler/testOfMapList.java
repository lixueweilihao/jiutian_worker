package com.play.Buidler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lihao
 * @Date: Create in 17:16 2017/12/15
 * @Description:
 * @Modified By:
 */
public class testOfMapList {
    public static void main(String[] args) {
        Map<String,Integer> mapTest = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        Map<String,Integer> map = new HashMap<>();
        map.put("1",4);
        map.put("2",5);
        map.put("3",7);
        for(int i=0;i<list.size();i++){
            for(Map.Entry<String,Integer> str : map.entrySet()){
                if (list.get(i).equals(str.getKey())){


                }
            }
        }
    }
}
