package com.aaron.springcloud.wx.message;

import com.aaron.springcloud.wx.constants.MessageTypeEnums;
import com.aaron.springcloud.wx.constants.MessageUrl;
import com.aaron.springcloud.wx.message.msgbody.MiniProgram;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/5
 */
@Setter
@Getter
@ToString (callSuper = true)
public class MiniProgramMessage extends CostumerMessage
{
    private MiniProgram miniprogrampage;


    public MiniProgramMessage(MiniProgram miniprogrampage)
    {
        super(MessageTypeEnums.MINI_PROGRAM.getType(), MessageUrl.COSTUMER_MESSAGE_URL);

        this.miniprogrampage = miniprogrampage;
    }
}
