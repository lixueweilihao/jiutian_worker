package Buidler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lihao
 * @Date: Create in 9:23 2017/12/20
 * @Description:
 * @Modified By:
 */
public class mapTest {
    boolean flag = true;

    public Map<Object, Double> init(Map<String, Double> map) {
        Map<Object, Double> maptest = new HashMap<>();
        for (Map.Entry<String, Double> me : map.entrySet()) {
            maptest.put(me.getKey(), 0.0);
        }
        return maptest;
    }

    Map<Object, Double> mapOne = null;

    public void changStatus(Map<String, Double> mapTest) {
        if (flag) {
            mapOne = init(mapTest);
            flag = false;
        }
        for (Map.Entry<String, Double> meT : mapTest.entrySet()) {
            for (Map.Entry<Object, Double> meO : mapOne.entrySet()) {
                if (meT.getKey().equals(meO.getKey()) && meT.getValue() > meO.getValue()) {
                    mapOne.put(meT.getKey(), meT.getValue());
                }
            }
        }
        System.out.println(mapOne);
    }

    public static void main(String[] args) {
        mapTest mt = new mapTest();
        Map<String, Double> mapTwo = new HashMap<>();
        mapTwo.put("a", (double) 5);
        mapTwo.put("b", (double) 6);
        mapTwo.put("c", (double) 7);
        mt.changStatus(mapTwo);
        Map<String, Double> mapO = new HashMap<>();
        mapO.put("a", (double) 9);
        mapO.put("b", (double) 4);
        mapO.put("c", (double) 6);
        mt.changStatus(mapO);
        Map<String, Double> mapOt = new HashMap<>();
        mapOt.put("a", (double) 12);
        mapOt.put("b", (double) 3);
        mapOt.put("c", (double) 30);
        mt.changStatus(mapOt);


    }
}
