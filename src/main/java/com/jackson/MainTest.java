package com.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/16  14:53
 */
public class MainTest {
     ObjectMapper mapper = new ObjectMapper();
    public void toJson() throws JsonProcessingException {
        MyValue mv = new MyValue("lihao",20);
        String jsonBytes = mapper.writeValueAsString(mv);
        System.out.println(jsonBytes);
    }
    public void toObject() throws IOException {
        MyValue value = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
        System.out.println(value.getAge());
        String jsonString="[{\"name\":\"lihao\",\"age\":20},{\"name\":\"lixiang\",\"age\":22}]";

        List<MyValue> beanList = mapper.readValue(jsonString, new TypeReference<List<MyValue>>() {});
        beanList.stream().forEach(e-> System.out.println(e.getAge()));
    }

    public static void main(String[] args) throws IOException {
      MainTest mt = new MainTest();
      mt.toObject();
    }
}
