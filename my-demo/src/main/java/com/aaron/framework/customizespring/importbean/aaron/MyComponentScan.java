package com.aaron.framework.customizespring.importbean.aaron;

import com.aaron.framework.customizespring.importbean.CustomizeImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2019-06-04
 */
@Import (CustomizeImportBeanDefinitionRegistrar.class)
public @interface MyComponentScan
{
    String[] packages();
}
