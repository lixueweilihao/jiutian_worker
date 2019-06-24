package com.play;

import java.io.*;

/**
 * Created on 2019-06-21
 *
 * @author :hao.li
 */
public class Utils {
    public static void main(String[] args) throws IOException {
        BufferedReader in =new BufferedReader(new FileReader("/Users/lixuewei/workspace/private/jiutian_worker/jiutian-others/src/main/resources/conf.txt"));
        String str;

        while ((str=in.readLine())!=null){
            String[] s = str.split("\\|");
            String message="<tr>\n";
            for(String str1:s){
                message+="<td>"+str1+"</td>\n";
            }
            message+="</tr>\n";
            System.out.print(message);
        }

//        System.out.println(message);
    }
}
