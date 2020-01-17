package kafka.kafka08;


import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created on 2020-01-10
 *
 * @author :hao.li
 */
public class demo {
    public static void main(String[] args) {
        SimpleDateFormat fd = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间

        String now = fd.format(calendar.getTime());//当前系统时间
        System.out.println("====now=====" + now);

    }

}
