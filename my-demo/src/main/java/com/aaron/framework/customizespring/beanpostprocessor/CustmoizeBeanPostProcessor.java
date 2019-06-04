package com.aaron.framework.customizespring.beanpostprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/23
 */
@Component
public class CustmoizeBeanPostProcessor implements BeanPostProcessor
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustmoizeBeanPostProcessor.class);


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
    {
        LOGGER.info("=====bean初始化之前=====" + bean);

        Controller annotation = bean.getClass().getAnnotation(Controller.class);

        if (annotation != null)
        {
            LOGGER.info(bean + " 是一个控制器");
        }

        if (bean instanceof Function)
        {
            LOGGER.info("----------------" + ((Function<String, Integer>)bean).apply("23"));
        }

        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
    {
        LOGGER.info("=====bean初始化之后=====" + bean);
        return bean;
    }
}
