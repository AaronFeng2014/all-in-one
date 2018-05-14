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
public class ServiceCenterBeanDefinitionParse implements BeanDefinitionParser
{

    private static final String HOST = "host";

    private static final String PORT = "port";


    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext)
    {
        String host = element.getAttribute(HOST);
        String port = element.getAttribute(PORT);

        System.out.println("host = " + host + ",  port = " + port);

        return null;
    }
}
