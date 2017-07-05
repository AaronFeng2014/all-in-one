package com.aaron.beginner.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-08-06
 */
public class TestCglibProxy
{
    public static void main(String[] args)
    {
        CglibProxy cglibProxy = new CglibProxy();

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(UserService.class);

        enhancer.setCallback(cglibProxy);

        UserService userService = (UserService)enhancer.create();

        Integer age = userService.getAge(18);
        String name = userService.getName("Aaron");

        System.out.println(name + " is " + age);

    }
}
