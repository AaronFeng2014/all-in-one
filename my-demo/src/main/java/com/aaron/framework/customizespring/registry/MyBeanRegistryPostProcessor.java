package com.aaron.framework.customizespring.registry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/30
 */
@Component
public class MyBeanRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor
{
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException
    {
        BeanDefinition beanDefinition = new GenericBeanDefinition();

        ((GenericBeanDefinition)beanDefinition).setBeanClass(BeanRegistryBean.class);
        beanDefinition.setAutowireCandidate(false);
        beanDefinition.setLazyInit(false);
        beanDefinition.setScope("singleton");
        beanDefinition.setAttribute("name", "fenghaixin");
        ((GenericBeanDefinition)beanDefinition).setInitMethodName("init");

        beanDefinition.setPrimary(false);

        registry.registerBeanDefinition("BeanRegistryBean", beanDefinition);
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
    {

    }
}
