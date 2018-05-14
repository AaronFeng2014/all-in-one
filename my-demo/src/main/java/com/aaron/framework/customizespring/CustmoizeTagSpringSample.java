package com.aaron.framework.customizespring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/14
 */
public class CustmoizeTagSpringSample
{

    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("customize-spring.xml");

        System.out.println(context);
    }
}
