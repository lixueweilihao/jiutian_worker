package com.ips.classloader.ClassLoader;

import java.lang.reflect.Method;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/9/29  19:11
 */
public class ExecutorProxy implements Executor {
    private String version;
    private StandardExecutorClassLoader classLoader;

    public ExecutorProxy(String version) {
        this.version = version;
        classLoader = new StandardExecutorClassLoader(version);
    }

    @Override
    public void execute(String name) {
        try {
            // Load ExecutorProxy class
            Class<?> executorClazz = classLoader.loadClass("Executor" + version.toUpperCase());

            Object executorInstance = executorClazz.newInstance();
            Method method = executorClazz.getMethod("execute", String.class);

            method.invoke(executorInstance, name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


