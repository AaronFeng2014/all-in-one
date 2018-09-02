package com.aaron.netty.channelhandler.in;

import com.aaron.netty.listener.MyChannelFutureListener;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 自定义的入站处理器，继承自SimpleChannelInboundHandler，其泛型定义了处理数据的类型
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-09-02
 */
public class MySimpleChannelInboundHandler extends SimpleChannelInboundHandler<ByteBuf>
{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf message) throws Exception
    {
        String msg = message.toString(CharsetUtil.UTF_8);
        ChannelFuture channelFuture = ctx.writeAndFlush(Unpooled.copiedBuffer("我接收到了你发送的消息，消息内容是：" + msg, CharsetUtil.UTF_8));

        channelFuture.addListener(new MyChannelFutureListener());
    }
}
