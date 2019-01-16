package com.aaron.component.netty.channelhandler.in;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 心跳检查机制
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-09-02
 */
@Slf4j
public abstract class AbstractHeartBeatChannelHandler extends SimpleChannelInboundHandler<ByteBuf>
{

    private static final String PING = "ping";

    private static final String PONG = "pong";


    public AbstractHeartBeatChannelHandler()
    {
        this(false);
    }


    public AbstractHeartBeatChannelHandler(boolean autoRelease)
    {
        super(autoRelease);
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception
    {
        if (evt instanceof IdleStateEvent)
        {
            IdleState idleState = ((IdleStateEvent)evt).state();

            switch (idleState)
            {
                case READER_IDLE:
                    handleReadIdle(ctx);
                    break;

                case ALL_IDLE:
                    handleAllIdle(ctx);
                    break;

                default:
                    break;
            }
        }
        else
        {
            super.userEventTriggered(ctx, evt);
        }
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception
    {

        String data = byteBuf.toString(CharsetUtil.UTF_8);

        //接收到ping数据
        if (PING.equals(data))
        {
            log.info("接收到ping请求，准备返回pong数据");
            sendPongMessage(channelHandlerContext);

        }
        else if (PONG.equals(data))
        {
            log.info("接收到ping数据的返回信息：{}", data);
        }
        else
        {
            handleData(channelHandlerContext, byteBuf);
        }
    }


    private void sendPongMessage(ChannelHandlerContext channelHandlerContext)
    {
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(PONG, CharsetUtil.UTF_8));
    }


    ChannelFuture sendPingMessage(ChannelHandlerContext channelHandlerContext)
    {
        return channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(PING, CharsetUtil.UTF_8));
    }


    protected void handleAllIdle(ChannelHandlerContext ctx)
    {

    }


    protected void handleReadIdle(ChannelHandlerContext ctx)
    {
        log.info("服务端读取数据超时...向客户端发送ping请求");
        sendPingMessage(ctx);
    }


    protected void handleData(ChannelHandlerContext ctx, ByteBuf msg)
    {
        //默认交给下一个处理器处理数据
        ctx.fireChannelRead(msg);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {

        //读取客户端发送的数据完成时，调用该方法
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        log.error("心跳检查发生了异常了", cause);
    }
}
