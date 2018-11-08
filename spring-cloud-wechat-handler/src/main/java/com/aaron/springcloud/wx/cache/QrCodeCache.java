package com.aaron.springcloud.wx.cache;

import com.aaron.springcloud.wx.domain.QrCodeCacheItem;

/**
 * 二维码资源缓存接口
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
public interface QrCodeCache
{
    /**
     * 保存二维码到缓存系统中
     *
     * @param hash long：建议使用二维码场景值对应的hash
     * @param qrCodeCacheItem QrCodeCacheItem：二维码缓存项
     */
    void saveQrCode(long hash, QrCodeCacheItem qrCodeCacheItem);


    /**
     * 从缓存系统中获取二维码
     *
     * @param hash long：建议使用二维码场景值对应的hash
     *
     * @return 返回二维码的地址，可直接访问的地址
     */
    String getQrCode(long hash);
}