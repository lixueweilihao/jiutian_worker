package com.play.Buidler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test {
   static String a = "{\"characteristicDimensionCode\":\"EppAccountNo\"," +
           "\"characteristicDimensionValue\":\"0000000000192031580\"," +
           "\"dimensionCodeAndValueCombinedKey\":\"EppAccountNo_0000000000192031580\"," +
           "\"riskCharacteristicCode\":\"CSIRCDS_INDEX_108\",\"riskCharacteristicValue\":" +
           "\"73615604238086144||80282204346871808||80326523998494720||80515762568658944||80563241766936576||DE0A8C5C-E42F-4E75-9ADC-AA971E984C56||80637092688883712\"," +
           "\"indicatorComputeTime\":\"1515556798161\",\"indicatorTimeOut\":\"518400\"}";
   static String b = "{\"characteristicDimensionCode\":\"IP\",\"characteristicDimensionValue\":\"117.135.227.160\",\"dimensionCodeAndValueCombinedKey\":\"IP_117.135.227.160\",\"riskCharacteristicCode\":\"CSIRCDS_INDEX_520\",\"riskCharacteristicValue\":\"1\",\"indicatorComputeTime\":\"1515556798611\",\"indicatorTimeOut\":\"10\"}";
    public void filter(String str){
        String pattern1 = "\"characteristicDimensionCode\":\"[\\w\\s:\\-]+";
        String pattern2 = "\"characteristicDimensionValue\":\"[\\w\\s:\\-]+";
        String pattern3 = "\"dimensionCodeAndValueCombinedKey\":\"[\\w\\s:\\-]+";
        String pattern4 = "\"riskCharacteristicCode\":\"[\\w\\s:\\-]+";
        String pattern5 = "\"riskCharacteristicValue\":\"[(\\w\\s:\\-)+(||)]+";
        String pattern6 = "\"indicatorComputeTime\":\"[\\w\\s:\\-]*";
        String pattern7 = "\"indicatorTimeOut\":\"[\\w\\s:\\-]*";

        Pattern r1 = Pattern.compile(pattern1);
        Pattern r2 = Pattern.compile(pattern2);
        Pattern r3 = Pattern.compile(pattern3);
        Pattern r4 = Pattern.compile(pattern4);
        Pattern r5 = Pattern.compile(pattern5);
        Pattern r6 = Pattern.compile(pattern6);
        Pattern r7 = Pattern.compile(pattern7);
        Matcher m1 = r1.matcher(a);
        Matcher m2 = r2.matcher(a);
        Matcher m3 = r3.matcher(a);
        Matcher m4 = r4.matcher(a);
        Matcher m5 = r5.matcher(a);
        Matcher m6 = r6.matcher(a);
        Matcher m7 = r7.matcher(a);
        StringBuffer bodyoutput = new StringBuffer();
        if(m1.find()&&m2.find()&&m3.find()&&m4.find()&&m5.find()&&m6.find()&&m7.find()){
            bodyoutput = bodyoutput.append("{"+m1.group(0) + (",") + m2.group(0) + "," + m3.group(0) + "," + m4.group(0) + "," + m5.group(0)+","+m6.group(0)+","+m7.group(0)+"}");

            System.out.println(bodyoutput);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        test t = new test();
        long t1=System.currentTimeMillis();
        System.out.println(t1);
        t.filter(a);
        Thread.sleep(5000);
        long t2 = System.currentTimeMillis();
        System.out.println(t2);
        System.out.println((t2-t1)/1000);
    }

}
