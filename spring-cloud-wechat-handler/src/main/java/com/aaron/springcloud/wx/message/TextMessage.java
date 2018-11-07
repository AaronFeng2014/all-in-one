package com.aaron.springcloud.wx.message;

import com.aaron.springcloud.wx.constants.MessageTypeEnums;
import com.aaron.springcloud.wx.constants.MessageUrl;
import com.aaron.springcloud.wx.message.msgbody.Text;

/**
 * 客服消息之文本消息
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public class TextMessage extends CostumerMessage
{

    private Text text;


    public TextMessage(Text text)
    {
        super(MessageTypeEnums.TEXT.getType(), (MessageUrl.COSTUMER_MESSAGE_URL));

        this.text = text;
    }
}
