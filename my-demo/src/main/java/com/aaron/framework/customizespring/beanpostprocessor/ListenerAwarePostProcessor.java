package com.aaron.framework.customizespring.beanpostprocessor;

import com.aaron.framework.customizespring.aware.ListenerAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-01-30
 */
@Component
public class ListenerAwarePostProcessor implements BeanPostProcessor
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerAwarePostProcessor.class);


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
            LOGGER.info("{}是一个ListenerAware实例", bean.getClass().getSimpleName());
            ((ListenerAware)bean).setListener();
        }
    }
}
