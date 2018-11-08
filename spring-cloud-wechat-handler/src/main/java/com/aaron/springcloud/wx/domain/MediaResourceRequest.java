package com.aaron.springcloud.wx.domain;

import com.aaron.springcloud.wx.constants.MediaResourceTypeEnum;
import com.alibaba.fastjson.annotation.JSONField;
import java.net.URL;
import lombok.Getter;

/**
 * 微信上传素材
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
@Getter
public class MediaResourceRequest
{

    private String type;

    /**
     * 小程序或者服务号的appId，这里本可以不要
     * <p>
     * 但是上传素材的方法有锁，为了高并发下延迟计算获取accessToken
     */
    @JSONField (serialize = false)
    private String appId;

    private URL resourceUrl;


    public MediaResourceRequest(MediaResourceTypeEnum typeEnum, URL resourceUrl, String appId)
    {
        this.type = typeEnum.getType();
        this.resourceUrl = resourceUrl;
        this.appId = appId;
    }
}
