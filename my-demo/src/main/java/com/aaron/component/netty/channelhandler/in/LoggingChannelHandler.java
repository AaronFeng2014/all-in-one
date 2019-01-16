package com.aaron.component.netty.channelhandler.in;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 日志类ChannelHandler
 * <p>
 * 原样输出
 * <p>
 * <p>
 * SimpleChannelInboundHandler会自动释放对象，但是给出一个构造参数用于控制是否需要自动释放对象
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-09-03
 */
@Slf4j
public class LoggingChannelHandler extends SimpleChannelInboundHandler<ByteBuf>
{

    public LoggingChannelHandler()
    {
        this(false);
    }


    /**
     * @param autoRelease boolean：SimpleChannelInboundHandler中用于是否控制自动释放对象的参数
     */
    public LoggingChannelHandler(boolean autoRelease)
    {
        super(autoRelease);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception
    {
        log.info("接收到原始数据：{}", msg.toString(CharsetUtil.UTF_8));

        //如果数据需要后续的ChannelHandler处理,不能释放对象，而SimpleChannelInboundHandler自动释放了对象，所以这里要先调用下面的方法，增加引用
        // msg.retain();
        //如果数据需要后续的ChannelHandler处理，一定要调用下面方法，交给下一个ChannelHandler处理，否则下一个ChannelHandler接收不到数据
        ctx.fireChannelRead(msg);
    }
}
