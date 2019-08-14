package flink.java.opentsdb.client.builder;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2019-08-14
 *
 * @author :hao.li
 */
public class Test {
    static class Student {
        public String name;
        public long no;
        public Lihao lihao;
        Map<String, String> map;

        public Student(String name, long no, Map<String, String> map) {
            this.name = name;
            this.no = no;
            this.map = map;
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("lihao", "test");
        map.put("lixiang", "test");
        String s1 = new Gson().toJson(new Student("zhangsan", 110, map));
        System.out.println(s1);
    }
}

class Lihao {
    String age;
    String sex;

    Lihao(String age, String sex) {
        this.age = age;
        this.sex = sex;
    }
}


