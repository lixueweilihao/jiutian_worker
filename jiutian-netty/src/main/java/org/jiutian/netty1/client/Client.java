package org.jiutian.netty1.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import org.jiutian.netty1.utils.PrintHandler;

/**
 * Created on 2020-01-20
 *
 * @author :hao.li
 */
public class Client extends PrintHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        final ChannelFuture f = ctx.writeAndFlush("client active");
        f.addListener((ChannelFutureListener) future -> {
            assert f == future;
            ctx.close();
        });
    }

    public static void main(String[] args) throws InterruptedException {
        ClientUtils.startClient("localhost", 9999, new Client());
    }
}

