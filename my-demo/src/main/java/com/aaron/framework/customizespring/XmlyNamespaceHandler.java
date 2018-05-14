package com.aaron.framework.customizespring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/14
 */
public class XmlyNamespaceHandler extends NamespaceHandlerSupport
{
    @Override
    public void init()
    {
        this.registerBeanDefinitionParser("service-center", new ServiceCenterBeanDefinitionParse());
        this.registerBeanDefinitionParser("backup-path", new BackupPathBeanDefinitionParse());
    }
}
