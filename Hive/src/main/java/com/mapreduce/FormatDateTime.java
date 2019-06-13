package com.mapreduce; /**
 * Created by lihao on 2017/6/28.
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class FormatDateTime {
    Logger logger = Logger.getLogger("FormatDateTime.class");

    public String getLastDate(Date date){
//        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM-ddHHmmss");
        String str=sdf.format(date);
        System.out.println(str);
        return str;

    }
    public void toLongDateString(Date dt){
        SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//        return myFmt1.format(dt);
//        return myFmt1.parse(d);
        String strdate = myFmt1.format(dt);
        System.out.print(strdate+"\n");
        try {
            Date date = myFmt1.parse(strdate);
            System.out.println(date);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void toCanlendar(Date dt){
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy");
        SimpleDateFormat myFmt2 = new SimpleDateFormat("MM");
        SimpleDateFormat myFmt3= new SimpleDateFormat("dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        Date date = cal.getTime();
        logger.info(myFmt.format(date));
        System.out.println(date+"\n");
        String str = myFmt1.format(date);
        System.out.println("--------------1---------------");
        System.out.println(str);
        System.out.println("--------------2---------------");
        logger.info(String.valueOf(cal.get(Calendar.YEAR)));
        logger.info(myFmt1.format(cal.get(Calendar.YEAR)));


        logger.info(String.valueOf(cal.get(Calendar.MONTH)+1));
        logger.info(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
//        cal.add(Calendar.YEAR,1);
//        System.out.println(cal.get(Calendar.YEAR));
    }
    public static void main(String args[]){
//        SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Date now = new Date();
//        String date = myFmt1.format(now);
//        System.out.println(date);
       FormatDateTime f = new FormatDateTime();
//       f.toLongDateString(now);
//        f.toCanlendar(now);
        f.getLastDate(now);
    }
}

