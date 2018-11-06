package com.aaron.wx.register;

import lombok.Getter;

/**
 * 微信事件回调枚举
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public class ReceiveMessageType
{

    /**
     * 微信普通消息，即用户主动给公众号发消息的事件枚举
     * 在微信回调参数中是以MsgType标记的
     */
    @Getter
    public enum ReceiveMessageEvent
    {
        /**
         * 发送文本消息
         */
        TEXT_MESSAGE("text", "文本消息"),

        /**
         * 发送图片消息
         */
        IMAGE_MESSAGE("image", "发送图片消息"),

        /**
         * 发送声音消息
         */
        VOICE_MESSAGE("voice", "发送声音消息"),

        /**
         * 发送视频消息
         */
        VIDEO_MESSAGE("video", "发送视频消息"),
        /**
         * 发短视频消息
         */
        SHORTVIDEO_MESSAGE("shortvideo", "发短视频消息"),

        /**
         * 发送地理位置消息
         */
        LOCATION_MESSAGE("location", "发送地理位置消息"),

        /**
         * 发送链接消息
         */
        LINK_MESSAGE("link", "发送链接消息");

        /**
         * 微信回调的事件类型
         */
        private String messageType;

        /**
         * 本地处理的方法名称
         */
        private String description;


        ReceiveMessageEvent(String messageType, String description)
        {
            this.messageType = messageType;
            this.description = description;
        }
    }


    /**
     * 微信事件推送枚举
     * <p>
     * 在微信回调参数中是以Event标记的
     */
    @Getter
    public enum ReceiveEventPushEnum
    {
        /**
         * 关注公众号事件
         */
        SUBSCRIBE_EVENT("subscribe", "关注公众号事件"),

        /**
         * 取消关注公众号事件
         */
        UNSUBSCRIBE_EVENT("unsubscribe", "取消关注公众号事件"),

        SCAN_EVENT("SCAN", ""),

        /**
         * 上报地理位置事件
         */
        LOCATION_EVENT("LOCATION", "上报地理位置事件"),

        /**
         * 点击自定义菜单事件
         * 注意：点击子菜单无效
         */
        CLICK_MENU_EVENT("CLICK", "点击自定义菜单事件"),

        /**
         * 点击菜单上的链接跳转事件
         */
        VIEW_MENU_EVENT("VIEW", "点击菜单上的链接跳转事件");

        /**
         * 微信推送的事件类型
         */
        private String eventType;

        /**
         * 本地处理的方法名称
         */
        private String description;


        ReceiveEventPushEnum(String eventType, String description)
        {
            this.eventType = eventType;
            this.description = description;
        }
    }
}