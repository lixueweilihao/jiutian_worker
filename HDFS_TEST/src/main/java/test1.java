import org.apache.velocity.runtime.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/12/19  11:10
 */
public class test1 {
    public static void main(String[] args) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+1);
        System.out.println(calendar.getTime());
//        System.out.println(date);
        long now = System.currentTimeMillis();
        long nextIntegrationPoint = (now / (1000*86400) + 1) * 86400 * 1000;
        System.out.println(nextIntegrationPoint);
//        long zero = now/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset();
        Long secondsNextEarlyMorning = getSecondsNextEarlyMorning();
        System.out.println(secondsNextEarlyMorning);
    }
    public static Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        // 改成这样就好了
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTimeInMillis());
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

}
