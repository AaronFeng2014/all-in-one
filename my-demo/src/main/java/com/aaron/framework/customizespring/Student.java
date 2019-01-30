package com.aaron.framework.customizespring;

import com.aaron.framework.customizespring.aware.ListenerAware;
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
public class Student implements ApplicationContextAware, ListenerAware
{
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
}
