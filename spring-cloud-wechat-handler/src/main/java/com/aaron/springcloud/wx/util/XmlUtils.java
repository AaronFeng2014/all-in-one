package com.aaron.springcloud.wx.util;

import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public class XmlUtils
{

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);


    public static Map<String, Object> parseToMap(String xml)
    {
        return parseXml(xml);
    }


    /**
     * 解析XML为Document对象
     *
     * @param xml 被解析的XMl
     *
     * @return Document
     */
    private static Map<String, Object> parseXml(String xml)
    {
        Document document;

        try
        {
            InputStream inputStream = IOUtils.toInputStream(xml, "UTF-8");

            SAXReader saxReader = new SAXReader();

            document = saxReader.read(inputStream);

        }
        catch (Exception e)
        {
            LOGGER.error("xml解析失败", e);
            return Collections.emptyMap();
        }

        if (document == null)
        {
            return Collections.emptyMap();
        }

        List<Element> elements = document.getRootElement().elements();

        return elements.stream().collect(Collectors.toMap(Element::getName, Element::getData));
    }
}