package com.aaron.netty.listener;

import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.GenericFutureListener;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

/**
 * 监听器
 * <p>
 * ChannelFutureListener接口继承自GenericFutureListener
 * <p>
 * 但是ChannelFutureListener接口中没有任何方法，全是定义的常量，来表示通用的处理流程
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-09-02
 */
@Slf4j
public class MyChannelFutureListener implements GenericFutureListener<ChannelFuture>
{
    @Override
    public void operationComplete(ChannelFuture future) throws Exception
    {
        log.info("消息处理完成，即将关闭channel，时间：{}", LocalDateTime.now());
    }
}
