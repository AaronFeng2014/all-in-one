package com.aaron.component.netty.codec.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 消息解码
 * Byte转换成Integer类型
 * <p>
 * ByteToMessageDecoder继承自ChannelHandlerInboundAdapter
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-09-02
 */
@Slf4j
public class ByteToIntegerMessageDecoder extends ByteToMessageDecoder
{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception
    {
        //首先需要检查可读取数据内容长度是否满足Integer的长度要求
        log.info("接收到数据，原始数据：{}", in.toString(CharsetUtil.UTF_8));
        //Integer长度为4
        if (in.readableBytes() >= 4)
        {
            out.add(in.readInt());
        }
    }


    @Override
    protected void decodeLast(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception
    {
        log.info("decodeLast invoke");
        out.add(in.readBytes(in.readableBytes()));
    }
}