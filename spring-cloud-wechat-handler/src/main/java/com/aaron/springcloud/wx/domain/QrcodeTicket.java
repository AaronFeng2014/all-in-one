package com.aaron.springcloud.wx.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/7
 */
public class QrcodeTicket
{
    private String ticket;

    @JSONField (name = "expire_seconds")
    private int expireSeconds;

    private String url;
}
