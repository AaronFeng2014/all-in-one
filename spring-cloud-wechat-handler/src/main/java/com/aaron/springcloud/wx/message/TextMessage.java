package com.aaron.springcloud.wx.message;

import com.aaron.springcloud.wx.constants.MessageTypeEnums;
import com.aaron.springcloud.wx.constants.MessageUrl;
import com.aaron.springcloud.wx.constants.QrCodeTypeEnum;
import com.aaron.springcloud.wx.domain.QrCodeRequest;
import com.aaron.springcloud.wx.message.msgbody.Text;
import com.alibaba.fastjson.JSON;

/**
 * 在客服消息中给用户发送文本消息
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public class TextMessage extends CostumerMessage
{

    private Text text;


    public TextMessage(Text text, String accessToken)
    {
        super(MessageTypeEnums.TEXT.getType(), MessageUrl.COSTUMER_MESSAGE_URL + accessToken);

        this.text = text;
    }


    public static void main(String[] args)
    {
        QrCodeRequest qrCodeRequest = new QrCodeRequest(QrCodeTypeEnum.QR_LIMIT_STR_SCENE,"werwet");

        System.out.println(JSON.toJSONString(qrCodeRequest));
    }
}
