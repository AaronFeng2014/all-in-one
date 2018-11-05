package com.aaron.wx.handler;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public abstract class AbstractMessageHandler implements MessageHandler
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Getter
    @Setter
    private String appId;


    @Override
    public boolean support(String appId)
    {
        Assert.notNull(this.appId, "未配置appId");


        return this.appId.equals(appId);
    }


    @Override
    public void doHandle(Map<String, Object> params, Consumer<Object> consumer)
    {

        if (params.get("").equals(true))
        {
            consumer.accept(params);
        }
        else
        {
            logger.error("未找到匹配的事件");
        }
    }
}