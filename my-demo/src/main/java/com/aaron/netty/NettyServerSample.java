package com.aaron.netty;

import com.aaron.netty.channelhandler.in.LoggingChannelHandler;
import com.aaron.netty.channelhandler.in.ServerChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import java.net.InetSocketAddress;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Netty是基于Java NIO和事件驱动的
 * <p>
 * Netty的主要构件
 * 1. Channel：是Java NIO的一个基本构件。代表一个到实体的开放连接，如读操作和写操作
 * 2. 回调：一个回调其实就是一个方法，一个指向已经被提供给另外一个方法的方法的引用，用于在适当是时候后者调用前者
 * 3. Future：提供了另一种在操作完成是通知应用程序的方式
 * 4. 事件和ChannelHandler：Netty使用不同的事件来通知我们状态的改变或者状态的操作，如记录日志，数据切换，流控制等
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-01-24
 */
@Slf4j
public class NettyServerSample
{

    public static void main(String[] args) throws InterruptedException
    {

        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(group).channel(NioServerSocketChannel.class)//指定channel类型为NioServerSocketChannel
                       .localAddress(new InetSocketAddress(8080));//指定绑定的本地地址

        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>()
        {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception
            {

                //连接空闲处理器
                IdleStateHandler idleStateHandler = new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS);

                socketChannel.pipeline().addLast(idleStateHandler).addLast(new LoggingChannelHandler())
                             .addLast(new FixedLengthFrameDecoder(4))
                             .addLast(new ServerChannelHandler());
            }

        });

        ChannelFuture channelFuture = serverBootstrap.bind().sync();

        channelFuture.channel().closeFuture().sync();

    }
}
