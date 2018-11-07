package com.aaron.springcloud.wx.message.msgbody;

import lombok.Getter;
import lombok.Setter;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/5
 */
@Setter
@Getter
public class MiniProgram
{

    private String title;

    private String appid;

    private String pagepath;

    private String thumb_media_id;
}
