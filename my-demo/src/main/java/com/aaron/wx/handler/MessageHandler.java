package com.aaron.wx.handler;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
interface MessageHandler
{
    /**
     * 消息是否支持被指定消息处理器处理，一般通过消息中的appid来标记
     *
     * @param appId String：appId，这里是微信发送的消息中携带的appId
     * @return 当消息能够被该消息器处理的时候返回true
     */
    boolean support(String appId);


    void doHandle(Map<String, Object> params, Consumer<Object> consumer);
}