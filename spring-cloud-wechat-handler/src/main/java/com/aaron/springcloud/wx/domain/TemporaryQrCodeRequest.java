package com.aaron.springcloud.wx.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-07
 */
@Setter
@Getter
@ToString
public class TemporaryQrCodeRequest extends QrCodeRequest
{

    @JSONField (name = "expire_seconds")
    private int expireSeconds;


    public TemporaryQrCodeRequest(String sceneStr)
    {
        this(2592000, sceneStr);
    }


    public TemporaryQrCodeRequest(int expireSeconds, String sceneStr)
    {
        super(sceneStr);

        this.expireSeconds = expireSeconds;
    }
}