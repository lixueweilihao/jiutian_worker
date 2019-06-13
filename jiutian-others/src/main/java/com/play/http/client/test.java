package com.play.http.client;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/9/16  15:59
 */
public class test {
    public static String abc = "/10.37.167.202_10.37.167.202-7180_418_1-0ab31801-b23c-4cbb-923c-0a64f0ed54a9";
    static String bc = "10.37.167.202-7180_418_1";
    public static void main(String[] args) {
        System.out.println(abc.contains(bc));

    }
    public static Long convertTimeToLong(String time) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
