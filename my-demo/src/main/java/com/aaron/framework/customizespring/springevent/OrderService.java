package com.aaron.framework.customizespring.springevent;

import com.aaron.framework.customizespring.springevent.event.OrderCancelEvent;
import com.aaron.framework.customizespring.springevent.event.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-01-22
 */
@Service
public class OrderService implements ApplicationEventPublisher
{

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    public void createOrder()
    {
        this.publishEvent(new OrderCreatedEvent("createOrder"));
    }


    //@Transactional (propagation = Propagation.REQUIRED)
    public void createOrderWithTransactional()
    {
        this.publishEvent(new OrderCreatedEvent("createOrderWithTransactional"));
    }


    public void cancelOrder()
    {
        this.publishEvent(new OrderCancelEvent("cancelOrder"));
    }


    @Override
    public void publishEvent(Object o)
    {
        applicationEventPublisher.publishEvent(o);
    }

}
