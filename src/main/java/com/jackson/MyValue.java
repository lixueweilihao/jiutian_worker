package com.jackson;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/16  14:53
 */
public class MyValue {
    public String name;
    public int age;

    public String getName() {
        return name;
    }

    public MyValue() {
    }

    public int getAge() {
        return age;

    }

    public MyValue(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
