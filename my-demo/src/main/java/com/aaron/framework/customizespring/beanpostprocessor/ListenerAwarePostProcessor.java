package com.aaron.framework.customizespring.beanpostprocessor;

import com.aaron.framework.customizespring.aware.ListenerAware;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-01-30
 */
public class ListenerAwarePostProcessor implements BeanPostProcessor
{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
    {
        return null;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
    {

        setListener(bean);

        return bean;
    }


    private void setListener(Object bean)
    {
        if (bean instanceof ListenerAware)
        {
            ((ListenerAware)bean).setListener();
        }
    }
}
