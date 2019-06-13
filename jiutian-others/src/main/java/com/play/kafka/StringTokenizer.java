package com.play.kafka;

public class StringTokenizer {
    static String str = "org.apache.hadoop.io.compress.GzipCodec,org.apache.hadoop.io.compress.DefaultCodec,\n" +
            "com.hadoop.compression.lzo.LzoCodec,com.hadoop.compression.lzo.LzopCodec,\n" +
            "org.apache.hadoop.io.compress.BZip2Codec";
    public static void main(String[] args) {
        java.util.StringTokenizer codecSplit = new java.util.StringTokenizer(str, ",");
        while (codecSplit.hasMoreElements()) {
            String codecSubstring = codecSplit.nextToken().trim();
            System.out.println(codecSubstring);
        }
    }
}
