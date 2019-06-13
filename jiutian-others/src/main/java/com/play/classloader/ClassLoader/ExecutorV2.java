package com.play.classloader.ClassLoader;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/9/29  19:08
 */
public class ExecutorV2 extends AbstractExecutor {

    @Override
    public void execute(final String name) {
        this.handle(new Handler() {
            @Override
            public void handle() {
                System.out.println("V2:" + name);
            }
        });
    }

}


