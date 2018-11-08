package com.aaron.springcloud.wx.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.Getter;

/**
 * 二维码缓存项
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
public class QrCodeCacheItem
{
    @Getter
    private String qrCodeUrl;

    private LocalDateTime createTime = LocalDateTime.now();

    /**
     * 过期时间
     */
    private long expiredSeconds;


    public QrCodeCacheItem(String qrCodeUrl)
    {
        /**
         *  -1 永不过期
         */
        this(qrCodeUrl, -1);
    }


    public QrCodeCacheItem(String qrCodeUrl, long expiredSeconds)
    {
        this.qrCodeUrl = qrCodeUrl;
        this.expiredSeconds = expiredSeconds;
    }


    /**
     * 二维码是否过期
     *
     * @return 返回true表示已经过期了
     */
    public boolean isExpired()
    {
        if (expiredSeconds == -1)
        {
            return false;
        }

        long between = ChronoUnit.SECONDS.between(createTime, LocalDateTime.now());

        //微信官方说是2592000秒最大时间
        return between > expiredSeconds;
    }
}