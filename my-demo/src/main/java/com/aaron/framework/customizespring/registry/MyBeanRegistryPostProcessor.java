package com.aaron.framework.customizespring.registry;

import com.aaron.framework.customizespring.aware.ListenerAware;
import com.aaron.framework.customizespring.importbean.aaron.Feng;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/30
 */
@Component
public class MyBeanRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, ListenerAware
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

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Feng.class));

        scanner.scan("com.aaron.framework.customizespring.importbean.aaron");
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
    {
        System.err.println("test-----");
    }


    @Override
    public void setListener()
    {
        System.err.println("setListener-----");
    }
}
