package com.aaron.springcloud.wx.constants;

import lombok.Getter;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-07
 */
@Getter
public enum QrCodeTypeEnum
{
    QR_LIMIT_STR_SCENE("QR_LIMIT_STR_SCENE"),

    QR_STR_SCENE("QR_STR_SCENE");

    private String type;


    QrCodeTypeEnum(String type)
    {
        this.type = type;
    }
}