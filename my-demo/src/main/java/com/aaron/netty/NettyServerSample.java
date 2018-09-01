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
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

/**
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
        ByteBuf byteBuf = Unpooled.copiedBuffer("This is my first netty demo! started at " + LocalDateTime.now(), Charset.forName("UTF-8"));

        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(group).channel(NioServerSocketChannel.class)//指定channel类型为NioServerSocketChannel
                       .localAddress(new InetSocketAddress(8080));//指定绑定的本地地址

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


                    /**
                     * 对每一个传入的消息都要调用该方法
                     *
                     * @param ctx
                     * @param msg
                     * @throws Exception
                     */
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
                    {

                        log.info("接受到消息了：{}", ((ByteBuf)msg).toString(CharsetUtil.UTF_8));
                        ctx.writeAndFlush(
                                Unpooled.copiedBuffer("已接收到你的消息：" + ((ByteBuf)msg).toString(CharsetUtil.UTF_8), CharsetUtil.UTF_8));
                    }


                    /**
                     * 通知channelInboundHandler最后一次对channelRead的调用时当前批量读取中的最后一条消息
                     *
                     * @param ctx ChannelHandlerContext
                     * @throws Exception：这里的异常需要自己捕获，如果自己不捕获的话你，那么将会作为
                     */
                    @Override
                    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
                    {
                        ctx.writeAndFlush(Unpooled.copiedBuffer("已接收到你的消息", CharsetUtil.UTF_8));
                    }


                    /**
                     * 在读取期间有异常时，会调用该方法
                     * <p>
                     * 如果该方法没有被实现，那么异常将会一直传递到channelHandlerContext的尾端，并被记录下来
                     *
                     * @param ctx
                     * @param cause
                     * @throws Exception
                     */
                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
                    {
                        log.error("读取异常", cause);
                    }
                });
            }

        });

        ChannelFuture channelFuture = serverBootstrap.bind().sync();

        channelFuture.channel().closeFuture().sync();

    }
}
