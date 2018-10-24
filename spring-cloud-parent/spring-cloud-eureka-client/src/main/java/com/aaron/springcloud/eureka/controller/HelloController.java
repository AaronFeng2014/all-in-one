package com.aaron.springcloud.eureka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-07-07
 */
@RestController
public class HelloController
{
    @RequestMapping ("/hello1")
    public String sayHello1()
    {
        return "This is my spring cloud app 1 !";
    }


    @RequestMapping ("/hello2")
    public String sayHello2()
    {
        return "This is my spring cloud app 2 !";
    }


    @RequestMapping ("/hello3")
    public String sayHello3()
    {
        return "This is my spring cloud app 3 !";
    }


    @RequestMapping ("/hello4")
    public String sayHello4()
    {
        return "This is my spring cloud app 4 !";
    }
}
