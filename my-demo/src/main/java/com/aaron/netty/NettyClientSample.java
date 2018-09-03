package com.aaron.netty;

import com.aaron.netty.channelhandler.in.ClientChannelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
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

            bootstrap.group(group)
                     .channel(NioSocketChannel.class)
                     .remoteAddress(new InetSocketAddress("localhost", 8080))
                     .handler(new ChannelInitializer<SocketChannel>()
                     {
                         @Override
                         protected void initChannel(SocketChannel channel) throws Exception
                         {

                             /*
                              * 所有的ChannelHandler被添加到ChannelPipeline中，被按顺序链式调用执行
                              *
                              * 该处，首先交由ByteToIntegerMessageDecoder处理，其次交给channelInboundHandler处理
                              */
                             channel.pipeline().addLast(new IdleStateHandler(0, 0, 5)).addLast(new ClientChannelHandler());
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
