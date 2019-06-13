import java.text.DateFormat;
import java.util.Date;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/18  10:43
 */
public class test2 {
    public static void main(String[] args) {
        System.out.println(DateFormat.getDateInstance().format(new Date()));
        String time = getTime(new Date());
        System.out.println(time);
    }

    public static String getTime(Date date) {
        int year = date.getYear();
        int month = date.getMonth() + 1;
        month = month < 10 ? ('0' + month) : month;
        int day = date.getDay();
        day = day < 10 ? ('0' + day) : day;
        return year + "-" + month + "-" + day;
    }
}
