package com.aaron.springcloud.consumer.utils;

import java.util.UUID;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/27
 */
public class UUIDUtil
{
    public static String getUuid()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
