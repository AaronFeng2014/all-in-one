package com.aaron.springcloud.consumer.controller;

import com.aaron.springcloud.api.Student;
import com.aaron.springcloud.api.UserServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Student sayHello(@PathVariable ("name") String name)
    {
        LOGGER.error("错误日志测试 {}", name);
        LOGGER.warn("警告日志测试 {}", name);
        LOGGER.debug("调试日志测试 {}", name);
        LOGGER.info("INFO日志测试 {}", name);
        LOGGER.trace("追踪日志测试 {}", name);

        return userService.queryUserInfo(8888L);
    }
}
