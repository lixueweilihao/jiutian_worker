package com.play.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/9/13  19:38
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Test test = new Test();
        test.jacksonInject();
    }
    public void jacksonInject() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = "{\"name\":\"myName\",\"age\":12}";
        TestPOJO testPOJO = objectMapper.readValue(jsonStr,TestPOJO.class);
        System.out.println(testPOJO.getAge());
        System.out.println(testPOJO.getName());
//        Assert.assertEquals("myName", testPOJO.getName());
//        Assert.assertEquals(12,testPOJO.getAge());
    }
    @JsonDeserialize(builder=TestPOJOBuilder.class)
    public static class TestPOJO{
        private String name;
        private int age;

        public TestPOJO(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
    @JsonPOJOBuilder(buildMethodName = "create",withPrefix = "with")
    public static class TestPOJOBuilder{
        private String name;
        private int age;

        public TestPOJOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public TestPOJOBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public TestPOJO create() {
            return new TestPOJO(name,age);
        }
    }

}
