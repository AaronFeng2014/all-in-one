package com.aaron.wx.message;

import com.aaron.wx.message.msgbody.MessageBody;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客服消息父类
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
@Setter
@Getter
@ToString
public class CostumerMessage<T extends MessageBody>
{
    @Getter
    @JSONField (deserialize = false)
    private String url;
    /**
     * 消息接收者
     */
    private String touser;

    /**
     * 消息类型
     */
    private String msgtype;

    private T body;
}