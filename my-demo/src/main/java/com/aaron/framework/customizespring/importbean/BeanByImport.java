package com.aaron.framework.customizespring.importbean;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-03-27
 */
public class BeanByImport implements ImportBeanDefinitionRegistrar
{
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry)
    {

        System.out.println(importingClassMetadata);
        System.out.println(registry);

    }
}
