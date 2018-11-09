package com.aaron.springcloud.wx.handler;

import java.util.Map;
import java.util.function.Consumer;

/**
 * 微信事件回调，自定义逻辑处理，在类实例需要注册到{@link MessageHandlerAdapterContext 的 messageHandlerAdapters属性}中
 * <p>
 * 注册地点在{@link com.aaron.springcloud.wx.AbstractMessageCallBackController}的 afterPropertiesSet方法中
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-09
 */
public interface MessageHandlerAdapter extends Consumer<Map<String, String>>
{

}