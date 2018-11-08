package com.aaron.springcloud.wx.cache.impl;

import com.aaron.springcloud.wx.cache.QrCodeCache;
import com.aaron.springcloud.wx.domain.QrCodeCacheItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 二维码内存缓存
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
public class QrCodeMemoryCacheRepository implements QrCodeCache
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MediaMemoryCacheRepository.class);

    private static final int MAX_CACHE_SIZE = 1000;

    /**
     * 二维码缓存
     */
    private static final Map<Long, QrCodeCacheItem> QRCODE_CACHE = new LinkedHashMap<Long, QrCodeCacheItem>()
    {

        @Override
        protected boolean removeEldestEntry(Map.Entry<Long, QrCodeCacheItem> eldest)
        {
            boolean overFlow = this.size() > MAX_CACHE_SIZE;

            if (overFlow)
            {
                LOGGER.warn("缓存容器已满，删除存留时间最长的数据，key：{}，QrCodeUrl：{}", eldest.getKey(), eldest.getValue().getQrCodeUrl());
            }

            return overFlow;
        }
    };


    @Override
    public void saveQrCode(long hash, QrCodeCacheItem qrCodeCacheItem)
    {
        QRCODE_CACHE.put(hash, qrCodeCacheItem);
    }


    @Override
    public String getQrCode(long hash)
    {
        QrCodeCacheItem qrCodeCacheItem = QRCODE_CACHE.get(hash);
        if (qrCodeCacheItem == null)
        {
            return "";
        }

        if (!qrCodeCacheItem.isExpired())
        {
            LOGGER.info("从缓存中加载到资源，QrCodeUrl：{}", qrCodeCacheItem.getQrCodeUrl());
            return qrCodeCacheItem.getQrCodeUrl();
        }

        //移除缓存
        LOGGER.info("资源已过期，从缓存中移除，QrCodeUrl：{}", qrCodeCacheItem.getQrCodeUrl());
        QRCODE_CACHE.remove(hash);

        return "";
    }
}
