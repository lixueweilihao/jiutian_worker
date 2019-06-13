package com.play.java8;

import java.text.DecimalFormat;

/**
 * @Author: lihao
 * @Date: Create in 20:23 2017/12/14
 * @Description:
 * @Modified By:
 */
public class apptest {
    public static void main(String[] args) {
        int a =50;
        double b =3;
        DecimalFormat df =  new DecimalFormat(("#.##"));
        double c = Double.parseDouble(df.format(a/b));
//        double c = Math.round(((a/b)*100)/100.0);
        System.out.println(c);
        System.out.println(a/b);
    }
}
