package com.aaron.framework.customizespring;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/16
 */
@Component
@Import ({ImportBean.class, CustomizeImportBeanDefinitionRegistrar.class})
public class CustomizeBeanFactoryProcessor implements BeanFactoryPostProcessor
{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
    {

        System.out.println("CustomizeBeanFactoryProcessor");
    }


    public static void main(String[] args)
    {
        LocalDateTime parse = LocalDateTime.parse("2015-12-23 21:58:44", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println(parse.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")));

        System.out.println("");
    }
}
