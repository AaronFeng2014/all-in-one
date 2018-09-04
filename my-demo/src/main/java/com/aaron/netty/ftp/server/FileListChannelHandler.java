package com.aaron.netty.ftp.server;

import com.aaron.netty.ftp.server.service.FileService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import java.io.File;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/9/4
 */
@Slf4j
@Component
public class FileListChannelHandler extends SimpleChannelInboundHandler<ByteBuf>
{
    @Autowired
    private FileService fileService;


    public FileListChannelHandler()
    {
        super(false);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        log.error("客户端已连接，id：{}", ctx.channel().id());
        ctx.writeAndFlush(Unpooled.copiedBuffer("欢迎登陆文件传输服务器，当前服务器时间：" + LocalDateTime.now(), CharsetUtil.UTF_8));

        StringBuilder stringBuilder = getFileList(new File(FtpServer.FTP_SERVER_RESOURCE_LOCATION));

        //向客户端会写服务器上指定的文件夹下的文件列表
        ctx.writeAndFlush(Unpooled.copiedBuffer(stringBuilder.toString(), CharsetUtil.UTF_8));
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception
    {

        String filePath = msg.toString(CharsetUtil.UTF_8);

        File file = new File(filePath);

        if (file.isDirectory())
        {
            StringBuilder stringBuilder = getFileList(file);

            //向客户端会写服务器上指定的文件夹下的文件列表
            ctx.writeAndFlush(Unpooled.copiedBuffer(stringBuilder.toString(), CharsetUtil.UTF_8));
        }
        else
        {
            byte[] fileToByteArray = FileUtils.readFileToByteArray(file);

            //向客户端直接返回文件数据
            ctx.write(Unpooled.copiedBuffer(fileToByteArray));

            ctx.writeAndFlush(Unpooled.copiedBuffer(FtpServer.END_LINE, CharsetUtil.UTF_8));
        }
    }


    private StringBuilder getFileList(File file)
    {
        List<String> allFileList = fileService.getAllFileList(file);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < allFileList.size(); i++)
        {
            stringBuilder.append(i).append(":").append(allFileList.get(i)).append("\n");
        }

        stringBuilder.append(FtpServer.END_LINE);
        return stringBuilder;
    }
}
