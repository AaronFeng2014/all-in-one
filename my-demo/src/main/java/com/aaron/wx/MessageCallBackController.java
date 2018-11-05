package com.aaron.wx;

import com.aaron.wx.handler.AbstractMessageHandler;
import com.aaron.wx.handler.MessageHandlerContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
@RestController
public class MessageCallBackController implements InitializingBean
{

    /**
     * 微信消息回调官方推荐返回
     * 并异步处理逻辑
     */
    private static final String SUCCESS = "SUCCESS";

    private MessageHandlerContext handlerContext = new MessageHandlerContext();


    @RequestMapping ("message/callback/{appId}")
    public String handle(@PathVariable ("appId") String appId, HttpServletRequest request)
    {

        return SUCCESS;
    }


    /**
     * 微信开发平台配置地址时，会检查连接是否可用，需要按要求原样返回请求内容
     *
     * @return
     */
    private boolean checkConnection()
    {

        return true;
    }


    /**
     * 异步，核心逻辑处理
     */
    @Async
    void doHandle()
    {
        handlerContext.handleChain(null);
    }


    @Override
    public void afterPropertiesSet() throws Exception
    {
        AbstractMessageHandler messageHandler = null;

        messageHandler.setAppId("");

        handlerContext.addMessageHandler(messageHandler).addMessageHandler(messageHandler);
    }
}