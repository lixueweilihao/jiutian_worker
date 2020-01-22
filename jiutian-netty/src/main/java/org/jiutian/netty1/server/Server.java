package org.jiutian.netty1.server;

import io.netty.channel.ChannelHandler;
import org.jiutian.netty1.utils.PrintHandler;

/**
 * Created on 2020-01-20
 *https://blog.csdn.net/m0_37681914/article/details/80541829
 * @author :hao.li
 */
@ChannelHandler.Sharable
public class Server extends PrintHandler {

    public static void main(String[] args) throws InterruptedException {
        ServerUtils.startServer(9999, new Server());
    }
}
