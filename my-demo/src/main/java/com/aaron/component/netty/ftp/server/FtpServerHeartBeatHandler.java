package com.aaron.component.netty.ftp.server;

import com.aaron.component.netty.channelhandler.in.AbstractHeartBeatChannelHandler;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-09-04
 */
public class FtpServerHeartBeatHandler extends AbstractHeartBeatChannelHandler
{

    public FtpServerHeartBeatHandler()
    {
        super(false);
    }
}
