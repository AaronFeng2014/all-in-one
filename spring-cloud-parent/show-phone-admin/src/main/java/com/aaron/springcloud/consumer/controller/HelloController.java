package com.aaron.springcloud.consumer.controller;

import com.aaron.springcloud.api.Student;
import com.aaron.springcloud.api.UserServiceFacade;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-05-24
 */
@RestController
public class HelloController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Value ("${jdbc.phone.username}")
    private String userName;

    @Value ("${jdbc.phone.url}")
    private String url;

    @Value ("${jdbc.phone.password}")
    private String password;

    @Value ("${driverClassName}")
    private String driverClassName;

    @Autowired
    private UserServiceFacade userService;


    /**
     * produces = "application/json" 会处理成json
     *
     * @param name
     * @return
     */
    @RequestMapping (value = "/hello/{name}", produces = "application/json")
    @ResponseBody
    public Student sayHello(@PathVariable ("name") String name, HttpServletRequest request)
    {

        LOGGER.info("请求原始地址：{}", request.getRequestURL().toString());
        LOGGER.info("getRemoteAddr：{}", request.getRemoteAddr());
        LOGGER.info("getRemoteUser：{}", request.getRemoteUser());

        LOGGER.info("姓名：{}", name);

        Student student = new Student();
        student.setAge(12);
        student.setName(name);

        return userService.queryUserInfo(student, 123L);
    }
}
