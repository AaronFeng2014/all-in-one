package com.aaron.netty.ftp.server;

import com.aaron.netty.ftp.server.service.FileService;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressiveFuture;
import io.netty.channel.ChannelProgressiveFutureListener;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import javax.activation.MimetypesFileTypeMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/9/4
 */
@Slf4j
@Component
@Scope ("prototype")
public class FileListChannelHandler extends SimpleChannelInboundHandler<HttpRequest>
{

    private static String TEMPLATE;
    private static String NOT_FOUND_TEMPLATE;

    @Autowired
    private FileService fileService;


    static
    {
        InputStream resourceAsStream = FileListChannelHandler.class.getClassLoader().getResourceAsStream("template/index.html");
        InputStream notFounfResourceAsStream = FileListChannelHandler.class.getClassLoader().getResourceAsStream("template/404.html");

        try
        {
            TEMPLATE = IOUtils.toString(resourceAsStream);
            NOT_FOUND_TEMPLATE = IOUtils.toString(notFounfResourceAsStream);
        }
        catch (IOException e)
        {
            TEMPLATE = "";
            log.error("页面读取错误", e);
        }
    }


    public FileListChannelHandler()
    {
        super(true);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        log.error("客户端已连接，id：{}", ctx.channel().id());
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest request) throws Exception
    {

        String filePath = "/".equals(request.uri()) ? FtpServer.FTP_SERVER_RESOURCE_LOCATION : request.uri();

        filePath = URLDecoder.decode(filePath, "UTF-8");

        File file = new File(filePath);

        GenericFutureListener<ChannelFuture> listener = channelFuture -> {

            log.info("数据发送完成，关闭连接");
            channelFuture.channel().close();
        };

        if (!file.exists())
        {
            HttpMessage message = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.NOT_FOUND,
                                                              Unpooled.copiedBuffer(NOT_FOUND_TEMPLATE, CharsetUtil.UTF_8));
            ctx.write(message);
            ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT).addListener(listener);

            return;
        }
        if (file.isDirectory())
        {
            String html = getFileList(file);

            //向客户端会写服务器上指定的文件夹下的文件列表
            HttpMessage message = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.copiedBuffer(html, CharsetUtil.UTF_8));
            ctx.write(message);

            ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT).addListener(listener);
        }
        else
        {
            //byte[] fileToByteArray = FileUtils.readFileToByteArray(file);

            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
            if (HttpHeaderValues.KEEP_ALIVE.toString().equals(request.headers().get("Connection")))
            {
                response.headers().add("Connection", HttpHeaderValues.KEEP_ALIVE);
            }
            response.headers().add("Content-Type", new MimetypesFileTypeMap().getContentType(file));
            response.headers().add("Content-Length", file.length());
            ctx.write(response);
            //向客户端直接返回文件数据
            //ctx.write(new ChunkedStream(new FileInputStream(file)));
            ChannelFuture channelFuture = ctx.write(new ChunkedFile(file, (int)file.length()), ctx.newProgressivePromise());
            channelFuture.addListener(new ChannelProgressiveFutureListener()
            {
                @Override
                public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) throws Exception
                {
                    System.out.println(progress + "/" + total);
                }


                @Override
                public void operationComplete(ChannelProgressiveFuture future) throws Exception
                {
                    System.out.println("done");
                }
            });

            ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT).addListener(listener);

        }
    }


    private String getFileList(File file)
    {
        List<String> allFileList = fileService.getAllFileList(file);

        String result = TEMPLATE.replaceFirst("\\{currentTime}", LocalDateTime.now().toString());
        result = result.replaceFirst("\\{parent}", "<a href=\"" + file.getName() + "\">" + file.getName() + "</a>");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < allFileList.size(); i++)
        {
            stringBuilder.append("<li><a href=\"").append(file.getName()).append("/").append(allFileList.get(i)).append("\">").append(
                    allFileList.get(i)).append("</a></li>");
        }

        return result.replaceFirst("\\{content}", stringBuilder.toString());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        log.error("捕获到异常信息", cause);
    }
}