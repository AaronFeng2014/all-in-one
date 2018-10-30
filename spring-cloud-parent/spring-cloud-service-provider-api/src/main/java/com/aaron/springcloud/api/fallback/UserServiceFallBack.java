package com.aaron.springcloud.api.fallback;

import com.aaron.springcloud.api.UserServiceFacade;
import com.aaron.springcloud.api.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/10/29
 */
@Component
public class UserServiceFallBack implements UserServiceFacade
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceFallBack.class);


    @Override
    public Student queryUserInfo(Student student, Long userId)
    {
        LOGGER.error("UserServiceFacade服务不可用，当前服务降级处理");

        student.setAge(-1);
        student.setName("error");

        return student;
    }
}