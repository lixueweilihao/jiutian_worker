package com.play.kafka;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2019-05-21
 *
 * @author :hao.li
 */
public class RegexMatches1 {
    private static final String REGEX = ".*.log*$";
    private static final String INPUT =
            "a.log";

    public static void main( String args[] ){
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // 获取 matcher 对象
        System.out.println(m.matches());
    }
}
