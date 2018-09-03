package com.aaron.netty.channelhandler.in;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/9/3
 */
@Slf4j
public class ServerChannelHandler extends AbstractHeartBeatChannelHandler
{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        log.info("This is my first netty demo! started at " + LocalDateTime.now());
    }


    @Override
    protected void handleReadIdle(ChannelHandlerContext ctx)
    {
        //服务端只是关心读取idle事件
        log.info("服务端读取数据超时...向客户端发送ping请求");

        //发送ping数据，检查客户端是否在线
        sendPingMessage(ctx);
    }


    @Override
    protected void handleData(ChannelHandlerContext ctx, ByteBuf msg)
    {

    }
}
