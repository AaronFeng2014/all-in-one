package com.aaron.netty.ftp.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

/**
 * ChannelPipeline 初始化
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/9/4
 */
@Component
public class ChannelPipelineInitializer extends ChannelInitializer<NioSocketChannel>
{

    @Override
    protected void initChannel(NioSocketChannel socketChannel) throws Exception
    {
        ChannelPipeline pipeline = socketChannel.pipeline();

        ChannelHandler idleHandler = new IdleStateHandler(10, 0, 0);
        FileListChannelHandler channelHandler = FtpServer.context.getBean(FileListChannelHandler.class);
        pipeline.addLast(idleHandler)
                .addLast(new FtpServerHeartBeatHandler())
                .addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(512 * 1024))
                .addLast(channelHandler);
    }
}
