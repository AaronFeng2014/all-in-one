package com.aaron.springcloud.controller;

import com.aaron.springcloud.api.UserServiceFacade;
import com.aaron.springcloud.api.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/10/29
 */
@RestController
public class UserController implements UserServiceFacade
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    /**
     * 参数中的注解要和接口中一致
     *
     * @param student
     *
     * @return
     */
    @Override
    public Student queryUserInfo(@RequestBody Student student, @PathVariable ("userId") Long userId)
    {
        LOGGER.info("接收到的student:{}", student);
        LOGGER.info("接收到的userId:{}", userId);

        student.setName(student.getName() + "-remote");
        student.setAge(Integer.valueOf(String.valueOf(userId)));

        return student;
    }
}
