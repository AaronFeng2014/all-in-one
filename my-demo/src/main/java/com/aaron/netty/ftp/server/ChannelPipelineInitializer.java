package com.aaron.netty.ftp.server;

import com.aaron.netty.channelhandler.in.ServerChannelHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ChannelHandler idleHandler = new IdleStateHandler(60 * 20, 0, 0);

    @Autowired
    private FileListChannelHandler fileListChannelHandler;


    @Override
    protected void initChannel(NioSocketChannel socketChannel) throws Exception
    {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(idleHandler).addLast(new ServerChannelHandler()).addLast(fileListChannelHandler);
    }
}
