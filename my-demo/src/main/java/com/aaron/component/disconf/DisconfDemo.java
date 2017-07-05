package com.aaron.component.disconf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-15
 */
public class DisconfDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-disconf.xml");

        SwitchVariable switchVariable = (SwitchVariable)context.getBean("switchVariable");

        System.out.println("从配置服务器获取到的内容：" + switchVariable);
        Thread.sleep(10000);
    }
}
