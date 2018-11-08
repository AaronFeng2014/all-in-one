package com.aaron.springcloud.wx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 向微信服务器提交消息
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public final class MessageSend
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSend.class);

    private static RestTemplate restTemplate = new RestTemplate();


    private MessageSend()
    {

    }



}
