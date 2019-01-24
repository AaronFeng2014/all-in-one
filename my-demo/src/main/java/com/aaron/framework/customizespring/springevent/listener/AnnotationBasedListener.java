package com.aaron.framework.customizespring.springevent.listener;

import com.aaron.framework.customizespring.springevent.event.OrderCancelEvent;
import com.aaron.framework.customizespring.springevent.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 基于注解的spring event
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-01-22
 */
@Component
public class AnnotationBasedListener
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationBasedListener.class);


    /**
     * 具有事物属性的监听器
     * <p>
     * 注意： 该监听器只能监听到在事物方法中发布的事件，非事物方法中发布的事件不会被该监听器接收
     *
     * 如果想让该事物监听器在非事物方法中执行， 需要加上 fallbackExecution = true
     */
    @TransactionalEventListener (phase = TransactionPhase.AFTER_COMMIT)
    public void createOrder(OrderCreatedEvent orderCreatedEvent)
    {
        LOGGER.info("这个订单已经创建，事物已经提交了，订单信息：{}", orderCreatedEvent.getSource());
    }


    @EventListener
    public void cancelOrder(OrderCancelEvent orderCancelEvent)
    {
        LOGGER.info("这个订单已经取消了，订单信息：{}", orderCancelEvent.getSource());
    }

}