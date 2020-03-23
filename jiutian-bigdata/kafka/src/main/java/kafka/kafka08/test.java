package kafka.kafka08;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2019-06-17
 *
 * @author :hao.li
 */
public class test {
    public static void main(String[] args) {
        Date date = new Date();
        //2019-05-30T09:01:01+0800
        SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+0800'");
        System.out.println(myFmt1.format(date));

    }

}
