package com.aaron.framework.customizespring.springevent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-01-22
 */
public class SpringEventTest
{

    /**
     * spring event机制： 可以和业务逻辑解耦
     * <p>
     * AbstractApplicationContext#refresh会依次初始化广播，和上下文中的applicationListener
     * <p>
     * 当使用ApplicationEventPublisher发布事件的时候，会使用SimpleApplicationEventMulticaster类处理时间， 调度到相应的listener中
     * <p>
     * SimpleApplicationEventMulticaster默认使用了同步处理方式，要使用异步，需要向其中设置一个线程池
     */
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.aaron.framework.customizespring.springevent");

        OrderService orderService = context.getBean(OrderService.class);

        orderService.createOrder();
        orderService.createOrderWithTransactional();
        orderService.cancelOrder();
    }
}