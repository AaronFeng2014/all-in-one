package com.aaron.springcloud.wx.message;

import com.aaron.springcloud.wx.constants.MessageTypeEnums;
import com.aaron.springcloud.wx.constants.MessageUrl;
import com.aaron.springcloud.wx.message.msgbody.Image;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/7
 */
public class ImageMessage extends CostumerMessage
{
    private Image image;


    public ImageMessage(Image image)
    {
        super(MessageTypeEnums.IMAGE.getType(), MessageUrl.COSTUMER_MESSAGE_URL);

        this.image = image;
    }
}
