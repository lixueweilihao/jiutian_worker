package com.play.classloader;

/**
 *
 * @Description：
 * @since 2018/4/25
 */
public class Config {
    private String name;

    private static boolean flag;
    static {
        flag = false;
        System.out.println("flag 的值为：" + flag);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
