package com.aaron.springcloud.wx.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public class EventRegister
{

    private static final Map<String, String> EVEN_MAPS = new HashMap<>();


    static
    {
        //文本消息事件
        EVEN_MAPS.put("text", "textHandle");

        //二维码扫描事件
        EVEN_MAPS.put("scan", "qrCodeScan");
    }


    public boolean isRegister(String msgType)
    {
        return EVEN_MAPS.containsKey(msgType);
    }


    /**
     *
     */
    public void registerMessageEvent(Map<String, String> event)
    {

    }


    public void addMessageEvent()
    {

    }
}