package com.aaron.framework.customizespring.springevent.listener;

import com.aaron.framework.customizespring.springevent.event.OrderCancelEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-01-22
 */
@Component
public class OrderCancelListener implements ApplicationListener<OrderCancelEvent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCancelListener.class);


    @Override
    public void onApplicationEvent(OrderCancelEvent orderCancelEvent)
    {
        LOGGER.warn("订单被取消了，订单信息是：{}", orderCancelEvent.getSource());
    }
}