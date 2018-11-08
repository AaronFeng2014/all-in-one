package com.aaron.springcloud.wx.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.Getter;

/**
 * 临时媒体资源
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
public class MediaCacheItem
{
    @Getter
    private String mediaId;

    private LocalDateTime createTime = LocalDateTime.now();


    public MediaCacheItem(String mediaId)
    {
        this.mediaId = mediaId;
    }


    /**
     * 临时素材是否过期
     *
     * @return 返回true表示已经过期了
     */
    public boolean isExpired()
    {
        long between = ChronoUnit.HOURS.between(createTime, LocalDateTime.now());

        //微信官方说是3天过期，这里取70个小时吧
        return between > 70;
    }
}