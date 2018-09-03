package com.aaron.netty.codec;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * CombinedChannelDuplexHandler把入站处理器和出站处理器结合在一起，通过泛型设置入站和出站处理器类型
 * 简化了代码的编写，当然这一切还是要看个人的编码习惯
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-09-04
 */
public class MyCombinedChannelDuplexHandler extends CombinedChannelDuplexHandler
{
}
