package com.aaron.netty.channelhandler.in;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/9/3
 */
@Slf4j
public class ClientChannelHandler extends AbstractHeartBeatChannelHandler
{

    private volatile int count = 0;


    @Override
    protected void handleAllIdle(ChannelHandlerContext ctx)
    {
        //读取或者写数据idle
        log.info("客户端读取或者写入数据超时...向服务端端发送ping请求");

        sendPingMessage(ctx);

        if (count++ == 10)
        {
            log.info("关闭channel");
            ctx.channel().close();
        }
    }


    @Override
    protected void handleData(ChannelHandlerContext ctx, ByteBuf msg)
    {

    }
}
