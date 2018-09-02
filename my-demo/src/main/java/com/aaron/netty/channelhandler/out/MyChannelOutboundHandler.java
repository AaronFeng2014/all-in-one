package com.aaron.netty.channelhandler.out;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-09-02
 */
@Slf4j
public class MyChannelOutboundHandler extends ChannelOutboundHandlerAdapter
{
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception
    {
        super.write(ctx, msg, promise);

        promise.addListener((d) -> {
            log.info("数据写入完成");
        });
    }
}
