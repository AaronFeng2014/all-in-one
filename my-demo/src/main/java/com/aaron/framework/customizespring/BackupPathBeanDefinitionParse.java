package com.aaron.framework.customizespring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/14
 */
public class BackupPathBeanDefinitionParse implements BeanDefinitionParser
{

    private static final String FILE_SYSTEM = "file-system";

    private static final String NETWORK = "network";


    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext)
    {
        String fileSystem = element.getAttribute(FILE_SYSTEM);

        String network = element.getAttribute(NETWORK);

        System.out.println("filesystem = " + fileSystem + ",  network = " + network);

        return null;
    }
}
