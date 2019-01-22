package com.aaron.framework.customizespring.springevent.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-01-22
 */
public class OrderChangeEvent extends ApplicationEvent
{
    public OrderChangeEvent(Object source)
    {
        super(source);
    }
}
