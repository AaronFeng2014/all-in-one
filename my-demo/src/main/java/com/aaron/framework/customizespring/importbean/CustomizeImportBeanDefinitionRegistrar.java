package com.aaron.framework.customizespring.importbean;

import com.aaron.framework.customizespring.importbean.aaron.Feng;
import com.aaron.framework.customizespring.importbean.aaron.MyComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/16
 */
public class CustomizeImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizeImportBeanDefinitionRegistrar.class);


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry)
    {
        LOGGER.info("CustomizeImportBeanDefinitionRegistrar");

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);

        scanner.addIncludeFilter(new AnnotationTypeFilter(Feng.class));

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MyComponentScan.class.getName());

        Object packages = annotationAttributes.get("packages");

        scanner.scan((String[])packages);

    }
}
