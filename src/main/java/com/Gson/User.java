package com.Gson;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/16  14:34
 */
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public boolean isDeveloper() {
        return isDeveloper;
    }

    private String email;
    private Integer age;
    private boolean isDeveloper;

    public User(String name, String email, Integer age, boolean isDeveloper) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.isDeveloper = isDeveloper;
    }
}
