package com.aaron.framework.customizespring.beanfactorypostprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/16
 */
@Component
public class CustomizeBeanFactoryProcessor implements BeanFactoryPostProcessor, ApplicationContextAware
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizeBeanFactoryProcessor.class);


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
    {

        LOGGER.info("CustomizeBeanFactoryProcessor");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        LOGGER.info("applicationContext --> " + applicationContext);
    }
}
