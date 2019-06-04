package com.aaron.framework.customizespring.beanpostprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/16
 */
@Controller
public class ImportBean implements Function<String, Integer>, ApplicationContextAware
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportBean.class);


    @Override
    public Integer apply(String s)
    {
        return Integer.valueOf(s);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        LOGGER.info("applicationContext={}", applicationContext);
    }
}
