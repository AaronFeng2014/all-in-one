package com.aaron.netty.ftp.server.codec;

import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpRequestDecoder;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/9/4
 */
public class MyHttpRequestDecoder extends HttpRequestDecoder
{

    @Override
    protected HttpMessage createMessage(String[] initialLine) throws Exception
    {
        return super.createMessage(initialLine);
    }
}
