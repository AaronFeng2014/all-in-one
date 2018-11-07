package com.aaron.springcloud.wx.handler;

import com.aaron.springcloud.wx.util.XmlUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 消息处理器链
 *
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
    private List<MessageHandlerAdapterContext> messageHandlerList = new ArrayList<>();


    /**
     * 微信事件回调消息处理入口
     *
     * @param params String：微信的回调参数
     */
    public void handleMessageChain(String params)
    {
        if (CollectionUtils.isEmpty(messageHandlerList))
        {
            LOGGER.warn("没有配置消息处理器，消息不会被处理，原始消息内容：{}", params);

            return;
        }

        //xml参数解析
        Map<String, Object> formatParams = XmlUtils.parseToMap(params);

        //服务号或小程序appId
        String appId = (String)formatParams.get("ToUserName");

        // 发送者openid
        String senderOpenId = (String)formatParams.get("FromUserName");

        LOGGER.info("接收到来自{}的消息，消息内容是：{}", senderOpenId, JSON.toJSONString(formatParams));

        AtomicBoolean hasHandle = new AtomicBoolean(false);
        messageHandlerList.stream().filter(messageHandler -> messageHandler.support(appId)).forEach(messageHandler -> {

            messageHandler.doHandle(formatParams);

            hasHandle.set(true);
        });

        //一个处理器都没有被匹配到，提示一个警告信息
        if (!hasHandle.get())
        {
            LOGGER.warn("未找到对应的消息处理器，消息未被消费，请检查消息处理器配置是否正确配置，appId：{}", appId);
        }

    }


    public MessageHandlerContext addMessageHandler(MessageHandlerAdapterContext messageHandler)
    {
        Assert.notNull(messageHandler, "messageHandler不能为空");

        messageHandlerList.add(messageHandler);

        return this;
    }
}