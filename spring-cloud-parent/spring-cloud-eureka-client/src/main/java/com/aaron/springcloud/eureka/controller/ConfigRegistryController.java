package com.aaron.springcloud.eureka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-07-07
 */
@RestController
public class ConfigRegistryController
{
    @RequestMapping ("/hello")
    public String sayHello()
    {
        return "This is my spring cloud app!";
    }
}
