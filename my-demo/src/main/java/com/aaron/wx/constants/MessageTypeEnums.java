package com.aaron.wx.constants;

import lombok.Getter;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
@Getter
public enum MessageTypeEnums
{
    TEXT("text", "文本消息"),

    IMAGE("image", "图片消息");

    private String type;

    private String description;


    MessageTypeEnums(String type, String description)
    {
        this.type = type;
        this.description = description;
    }
}
