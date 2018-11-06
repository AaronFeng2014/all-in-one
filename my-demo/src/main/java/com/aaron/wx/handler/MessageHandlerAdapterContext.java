package com.aaron.wx.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * MessageHandlerAdapter
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public class MessageHandlerAdapterContext
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerAdapterContext.class);

    private static final Consumer<Map<String, Object>> DEFAULT_HANDLER_ADAPTER = params -> {

        //获取事件类型, 微信回调中事件类型和消息类型的区分,两者是不同的字段
        String type = params.getOrDefault("Event", params.getOrDefault("MsgType", "")).toString();

        LOGGER.warn("未注册相应的事件处理器，事件类型：{}", type);
    };
    /**
     * 消息处理适配器
     */
    private Map<String, Consumer<Map<String, Object>>> messageHandlerAdapter = new HashMap<>();

    private String appId;


    /**
     * 构造函数
     *
     * @param appId String：指明该处理器adapter处理的消息对应的appId
     */
    public MessageHandlerAdapterContext(String appId)
    {
        this.appId = appId;
    }


    /**
     * 消息是否支持被指定消息处理器处理，一般通过消息中的appid来标记
     *
     * @param appId String：appId，这里是微信发送的消息中携带的appId
     * @return 当消息能够被该消息器处理的时候返回true
     */
    boolean support(String appId)
    {
        Assert.isTrue(StringUtils.isNotEmpty(appId), "未配置appId");

        return this.appId.equals(appId);
    }


    void doHandle(Map<String, Object> params)
    {

        //获取事件类型, 微信回调中事件类型和消息类型的区分,两者是不同的字段
        String type = params.getOrDefault("Event", params.getOrDefault("MsgType", "")).toString();

        try
        {
            //查找事件注册的处理器adapter
            messageHandlerAdapter.getOrDefault(type, DEFAULT_HANDLER_ADAPTER).accept(params);
        }
        catch (Exception e)
        {
            LOGGER.error("事件处理错误，事件类型：{}", type, e);
        }
    }


    /**
     * 注册事件处理器，在接收到微信的回调后，只会关心已经注册过的事件
     *
     * @param eventType String： 参考{@link com.aaron.wx.register.ReceiveMessageType}中定义的枚举值
     * @param consumer Consumer<Map<String, Object>>：对应事件的处理器
     */
    public MessageHandlerAdapterContext addMessageHandlerAdapter(String eventType, Consumer<Map<String, Object>> consumer)
    {
        messageHandlerAdapter.put(eventType, consumer);

        return this;
    }
}