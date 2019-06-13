package com.ips.classloader;
/*
**https://blog.csdn.net/claram/article/details/52371940
 * */

public class ClassLoaderDemo1 {
    private Config config;
    public ClassLoaderDemo1(Config config){
        this.config=config;

    }
    public static void main(String [] args) throws IllegalAccessException, InstantiationException {
        try {
            ClassLoader system = ClassLoader.getSystemClassLoader();
            Class<Config> cls = null;
            System.out.println("----------方法1----------");
            cls = (Class<Config>)Class.forName("com.ips.classloader.Config");
            System.out.println(cls.newInstance().getName());
            System.out.println("----------方法2----------");
            cls = (Class<Config>)Class.forName("com.ips.classloader.Config", false, system);

            System.out.println("----------方法3----------");
            cls = (Class<Config>)Class.forName("com.ips.classloader.Config", true, system);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
