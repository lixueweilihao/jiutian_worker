package com.ibort.frame.server;

import java.io.IOException;

/**
 * @author jeffy
 */
public interface Server {

    /**
     * 停止
     */
    void stop();

    /**
     * 启动
     *
     * @throws IOException
     */
    void start() throws IOException;

    /**
     * 注册服务
     *
     * @param serviceInterface
     * @param impl
     */
    void register(Class serviceInterface, Class impl);

    /**
     * 判断是否在运行
     *
     * @return
     */
    boolean isRunning();

    /**
     * 获取端口号
     *
     * @return
     */
    int getPort();

}
