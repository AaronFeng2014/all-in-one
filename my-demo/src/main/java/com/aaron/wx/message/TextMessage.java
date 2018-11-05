package com.aaron.wx.message;

import com.aaron.wx.constants.MessageTypeEnums;
import com.aaron.wx.constants.MessageUrl;

/**
 * 客服消息之文本消息
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public class TextMessage extends CostumerMessage
{

    public TextMessage()
    {
        super.setUrl(MessageUrl.COSTUMER_MESSAGE_URL);
        super.setMsgtype(MessageTypeEnums.TEXT.getType());
    }
}
