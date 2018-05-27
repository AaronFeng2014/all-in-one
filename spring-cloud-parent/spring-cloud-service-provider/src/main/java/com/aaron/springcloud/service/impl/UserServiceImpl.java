package com.aaron.springcloud.service.impl;

import com.aaron.springcloud.api.Student;
import com.aaron.springcloud.api.UserServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/24
 */
@RestController
public class UserServiceImpl implements UserServiceFacade
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    /**
     * 参数中的注解要和接口中一致
     *
     * @param student
     * @return
     */
    @Override
    public Student queryUserInfo(@RequestBody Student student, @PathVariable ("userId") Long userId)
    {
        LOGGER.info("接收到的student:{}", student);
        LOGGER.info("接收到的userId:{}", userId);

        return student;
    }
}
