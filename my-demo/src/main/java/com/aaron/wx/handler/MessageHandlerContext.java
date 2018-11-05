package com.aaron.wx.handler;

import com.aaron.wx.util.XmlUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public class MessageHandlerContext
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerContext.class);

    /**
     * 消息处理链
     */
    private List<MessageHandler> messageHandlerList = new ArrayList<>();


    public MessageHandlerContext addMessageHandler(AbstractMessageHandler messageHandler)
    {
        return this;
    }


    public void handleChain(String params)
    {
        if (CollectionUtils.isEmpty(messageHandlerList))
        {
            LOGGER.warn("没有配置消息处理器");
            return;
        }

        //xml参数解析
        Map<String, Object> formatParams = XmlUtils.parseToMap(params);

        //服务号或小程序appId
        String appId = (String)formatParams.get("ToUserName");

        // 发送者openid
        String senderOpenId = (String)formatParams.get("FromUserName");

        LOGGER.info("接收到来自{}的消息，消息内容是：{}", senderOpenId, JSON.toJSONString(formatParams));

        messageHandlerList.stream().filter(messageHandler -> messageHandler.support(appId)).forEach(
                messageHandler -> messageHandler.doHandle(formatParams, null));
    }
}