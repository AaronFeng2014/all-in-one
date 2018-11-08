package com.aaron.springcloud.wx.message;

import com.aaron.springcloud.wx.constants.MessageTypeEnums;
import com.aaron.springcloud.wx.constants.MessageUrl;
import com.aaron.springcloud.wx.message.msgbody.Music;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
public class MusicMessage extends CostumerMessage
{

    private Music music;


    public MusicMessage(Music music, String accessToken)
    {
        super(MessageTypeEnums.MUSIC.getType(), MessageUrl.COSTUMER_MESSAGE_URL + accessToken);

        this.music = music;
    }
}
