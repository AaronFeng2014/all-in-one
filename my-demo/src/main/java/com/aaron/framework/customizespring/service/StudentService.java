package com.aaron.framework.customizespring.service;

import com.aaron.framework.customizespring.aware.ListenerAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-01-25
 */
@Component
public class StudentService implements ApplicationContextAware, ListenerAware
{

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        System.out.println("哈哈哈哈");
    }


    @Override
    public void setListener()
    {
        System.err.println("setListener-----");
    }


    public void introduce(String name)
    {
        LOGGER.warn("hello, i am {}", name);
    }
}
