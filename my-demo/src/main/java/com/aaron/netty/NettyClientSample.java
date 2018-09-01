package com.aaron.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;
import lombok.extern.slf4j.Slf4j;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-01-24
 */
@Slf4j
public class NettyClientSample
{

    public static void main(String[] args)
    {
        EventLoopGroup group = new NioEventLoopGroup();
        try
        {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress("localhost", 8080)).handler(
                    new ChannelInitializer<SocketChannel>()
                    {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception
                        {
                            channel.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>()
                            {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception
                                {
                                    ctx.writeAndFlush(Unpooled.copiedBuffer("我来了哦", CharsetUtil.UTF_8));
                                }


                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
                                {
                                    log.error("异常了哦", cause);
                                }


                                @Override
                                public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception
                                {
                                    log.info("接受到消息：{}", msg.toString(CharsetUtil.UTF_8));
                                    ctx.writeAndFlush(
                                            Unpooled.copiedBuffer("已接收到你的消息：" + msg.toString(CharsetUtil.UTF_8), CharsetUtil.UTF_8));
                                }
                            });
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().closeFuture().sync();
        }
        catch (InterruptedException ignore)
        {

        }
        finally
        {
            try
            {
                group.shutdownGracefully().sync();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
