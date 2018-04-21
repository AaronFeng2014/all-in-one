package com.aaron.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-01-24
 */
public class NettyServerSample
{

    public static void main(String[] args) throws InterruptedException
    {
        ByteBuf byteBuf = Unpooled.copiedBuffer("This is my first netty demo!", Charset.forName("UTF-8"));

        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(8080));

        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>()
        {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception
            {

                socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter()
                {

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception
                    {
                        try
                        {
                            ctx.writeAndFlush(byteBuf.duplicate()).addListener(ChannelFutureListener.CLOSE);

                        }
                        catch (Exception e)
                        {

                        }

                    }
                });
            }

        });

        ChannelFuture channelFuture = serverBootstrap.bind().sync();

        channelFuture.channel().closeFuture().sync();

    }
}
