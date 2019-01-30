package com.aaron.framework.customizespring.context;

import com.aaron.framework.customizespring.beanpostprocessor.ListenerAwarePostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-01-30
 */
public class MyApplicationContext extends ClassPathXmlApplicationContext
{
    public MyApplicationContext(String s)
    {
        super(s);
    }


    @Override
    protected void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory)
    {
        super.prepareBeanFactory(beanFactory);

        beanFactory.addBeanPostProcessor(new ListenerAwarePostProcessor());
    }
}
