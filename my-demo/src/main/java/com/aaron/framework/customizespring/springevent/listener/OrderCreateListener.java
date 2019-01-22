package com.aaron.framework.customizespring.springevent.listener;

import com.aaron.framework.customizespring.springevent.event.OrderCreatedEvent;
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
public class OrderCreateListener implements ApplicationListener<OrderCreatedEvent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreateListener.class);


    @Override
    public void onApplicationEvent(OrderCreatedEvent orderCreatedEvent)
    {
        LOGGER.info("订单创建好了哦，订单信息是：{}", orderCreatedEvent.getSource());
    }
}
