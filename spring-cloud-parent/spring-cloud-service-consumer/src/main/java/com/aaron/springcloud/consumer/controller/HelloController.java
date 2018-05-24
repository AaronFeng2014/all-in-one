package com.aaron.springcloud.consumer.controller;

import com.aaron.springcloud.api.Student;
import com.aaron.springcloud.api.UserServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-05-24
 */
@RestController
public class HelloController
{

    @Autowired
    private UserServiceFacade userService;


    @RequestMapping ("/hello/{name}")
    public Student sayHello(@PathVariable ("name") String name)
    {
        return userService.queryUserInfo(8888L);
    }
}
