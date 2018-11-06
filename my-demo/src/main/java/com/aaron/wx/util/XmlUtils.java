package com.aaron.wx.util;

import java.io.StringReader;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        Element element = parseXml(xml);

        Map<String, Object> map = new HashMap<>();
        listNodes(element, map);

        return map;

    }


    /**
     * 解析XML为Document对象
     *
     * @param xml 被解析的XMl
     * @return Document
     */
    private static Element parseXml(String xml)
    {
        Document document = null;

        StringReader sr = new StringReader(xml);
        SAXReader saxReader = new SAXReader();
        try
        {
            document = saxReader.read(sr);
        }
        catch (DocumentException e)
        {
            LOGGER.error("xml解析失败", e);
        }
        return null == document ? null : document.getRootElement();
    }


    /**
     * 递归解析xml节点，适用于 多节点数据
     */
    private static void listNodes(Element node, Map<String, Object> map)
    {
        if (null == node)
        {
            return;
        }

        // 初始化返回
        // 首先获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();

        // 遍历属性节点
        for (Attribute attribute : list)
        {
            map.put(attribute.getName(), attribute.getValue());
        }

        Iterator<Element> iterator = node.elementIterator();
        while (iterator.hasNext())
        {
            listNodes(iterator.next(), map);
        }
    }
}