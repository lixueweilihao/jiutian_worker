package com.play.Buidler;

import java.lang.reflect.Field;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/16  21:27
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Class clazz = User.class;
        User u = new User("小明");
        for (Field f : clazz.getDeclaredFields()) {
            System.out.println(f.isAccessible());//这里的结果是false
            f.setAccessible(true);
            System.out.println(f.getName() + ":" + f.get(u));
        }
    }
}
