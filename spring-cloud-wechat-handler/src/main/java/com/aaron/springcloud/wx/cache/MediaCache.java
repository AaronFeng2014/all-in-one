package com.aaron.springcloud.wx.cache;

/**
 * 微信素材上传，缓存接口，避免重复上传增加耗时，提升用户体验
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
public interface MediaCache
{
    /**
     * 保存媒体资源
     * 原始上传的文件地址作为缓存的key
     *
     * @param mediaId String：媒体资源id
     * @param key String：媒体资源对应的索引，即原始上传的文件地址
     */
    void saveMedia(String key, String mediaId);


    /**
     * 获取媒体资源
     *
     * @param mediaIndex String：媒体资源的地址，如http地址，更高级地可以是文件的hash形式
     *
     * @return 缓存中资源
     */
    String getMedia(String mediaIndex);
}
