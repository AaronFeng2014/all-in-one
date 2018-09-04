package com.aaron.netty.channelhandler.in;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
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

    private volatile int failCount = 0;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        log.info("This is my first netty demo! started at " + LocalDateTime.now());

        //下一个ChannelHandler继续处理
        ctx.fireChannelActive();
    }


    @Override
    protected void handleReadIdle(ChannelHandlerContext ctx)
    {
        //服务端只是关心读取idle事件
        log.info("服务端读取数据超时...向客户端发送ping请求");

        //发送ping数据，检查客户端是否在线
        sendPingMessage(ctx).addListener(channelFuture -> {

            if (!channelFuture.isSuccess() && failCount++ >= 5)
            {
                log.error("服务端主动关闭连接，失败次数大于5");
                ctx.channel().close();
            }
        });

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        log.error("异常", cause);
    }


    @Override
    protected void handleData(ChannelHandlerContext ctx, ByteBuf msg)
    {
        log.info("接收到来自客户端的信息：{}", msg.toString(CharsetUtil.UTF_8));
    }
}
