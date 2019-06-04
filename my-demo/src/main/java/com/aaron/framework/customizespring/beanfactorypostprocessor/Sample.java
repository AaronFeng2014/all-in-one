package com.aaron.framework.customizespring.beanfactorypostprocessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-06-04
 */
public class Sample
{
    public static void main(String[] args)
    {
        String packages = "com.aaron.framework.customizespring.beanfactorypostprocessor";

        ApplicationContext context = new AnnotationConfigApplicationContext(packages);

    }
}
