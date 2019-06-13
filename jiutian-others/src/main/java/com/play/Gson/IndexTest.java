package com.play.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/16  14:36
 */
public class IndexTest {
    User user;
    public void init(){
        user = new User("Norman","norman@futurestud.io",26,true);
    }
    public void index() {
        Gson gson = new Gson();
        System.out.println(gson.toJson(user));
        System.out.println("---------------");
        Gson gson1 = new GsonBuilder().create();
        System.out.println(gson1.toJson(user));
    }
    public void jsonToJava() {
        String jsonStr = "{\"name\":\"栗霖\",\"age\":\"18\"}";
        Gson gson = new GsonBuilder().create();
        User p = gson.fromJson(jsonStr,User.class);
        System.out.println("---->jsonStr convert javaBean " + p.getName() + " " + p.getAge());
    }

    public static void main(String[] args) {
        IndexTest tt = new IndexTest();
        tt.init();
        tt.index();
    }
}
