package com.aaron.framework.hessian;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-05-23
 */
public class Test
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        HessianInterface hessian = (HessianInterface)context.getBean("hessian");
        String hello = hessian.sayHello("fenghaixin");

        System.out.println(hello);

    }
}
