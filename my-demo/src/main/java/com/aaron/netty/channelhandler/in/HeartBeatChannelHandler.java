package com.aaron.netty.channelhandler.in;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 心跳检查机制
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-09-02
 */
@Slf4j
public class HeartBeatChannelHandler extends ChannelInboundHandlerAdapter
{

    //心跳检查消息
    private static final ByteBuf HEART_BEAT_CHECK = Unpooled.copiedBuffer("心跳检测", CharsetUtil.UTF_8);


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception
    {
        if (evt instanceof IdleStateEvent)
        {
            log.warn("心跳检查");

            //发送消息，并注册了一个监听器，该监听器的作用就是在连接失败的时候关闭连接，如果连接不关闭
            ctx.writeAndFlush(HEART_BEAT_CHECK.duplicate()).addListener(ChannelFutureListener.CLOSE);
        }
        else
        {
            super.userEventTriggered(ctx, evt);
        }
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        log.info("这是心跳检查数据：{}", ((ByteBuf)msg).toString(CharsetUtil.UTF_8));
        ReferenceCountUtil.release(msg);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        log.error("发生了异常了", cause);
        ctx.writeAndFlush(Unpooled.copiedBuffer("发生了异常了", CharsetUtil.UTF_8));
    }
}
