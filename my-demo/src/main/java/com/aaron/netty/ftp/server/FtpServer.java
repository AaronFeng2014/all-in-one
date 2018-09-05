package com.aaron.netty.ftp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于Netty实现的文件传输服务器服务端
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/9/4
 */
@Slf4j
public class FtpServer
{

    /**
     * socket传输数据结束符
     */
    public static final String END_LINE = "*&^_%!";

    /**
     * FTP服务器资源地址
     */
    public static final String FTP_SERVER_RESOURCE_LOCATION = "/Users/fenghaixin/logs";

    public static final String FTP_SERVER_RESOURCE_PREFFIX = "/Users/fenghaixin/";


    public static ApplicationContext context;


    public static void main(String[] args)
    {

        context = new ClassPathXmlApplicationContext("applicationContext-ftpServer.xml");

        ChannelPipelineInitializer initializer = context.getBean(ChannelPipelineInitializer.class);

        EventLoopGroup groupBoss = new NioEventLoopGroup();
        EventLoopGroup groupWorker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(groupBoss, groupWorker).localAddress(8080).channel(NioServerSocketChannel.class).childHandler(initializer);

        try
        {
            ChannelFuture future = serverBootstrap.bind().sync().addListener(channelFuture -> {

                if (channelFuture.isSuccess())
                {
                    log.info("ftp服务器启动成功");
                }
                else
                {
                    log.error("ftp服务器启动失败了");
                }

            });

            future.channel().closeFuture().sync();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }


    private void init()
    {

    }
}