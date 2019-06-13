package com.play.kafka;


public class RegexDemo01 {
    public static void main(String[] args) {
        String str = "111.3.22.11";

        str=str.replaceAll("(^|\\.)(\\d)(\\.|$)","$100$2$3");
        System.out.println(str);
        str=str.replaceAll("(^|\\.)(\\d{2})(\\.|$)","$10$2$3");
        System.out.println(str);
        str=str.replaceAll("(^|\\.)(\\d{2})(\\.|$)","$10$2$3");
        System.out.println(str);
        str=str.replaceAll("(^|\\.)(\\d{1})(\\.|$)","$100$2$3");

        System.out.println(str);
    }
}
