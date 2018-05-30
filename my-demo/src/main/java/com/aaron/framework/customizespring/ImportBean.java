package com.aaron.framework.customizespring;

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
    @Override
    public Integer apply(String s)
    {
        return Integer.valueOf(s);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        System.out.println(applicationContext);
    }
}
