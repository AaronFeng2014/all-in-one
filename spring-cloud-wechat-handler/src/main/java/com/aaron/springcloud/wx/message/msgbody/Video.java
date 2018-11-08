package com.aaron.springcloud.wx.message.msgbody;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
@Setter
@Getter
public class Video
{

    private String title;

    @JSONField (name = "thumb_media_id")
    private String thumb_media_id;

    @JSONField (name = "media_id")
    private String mediaId;

    private String description;
}
