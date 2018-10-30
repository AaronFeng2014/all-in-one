package com.aaron.springcloud.service.impl;

import com.aaron.springcloud.api.model.Student;
import com.aaron.springcloud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/24
 */
public class UserServiceImpl implements UserService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    /**
     * 参数中的注解要和接口中一致
     *
     * @return
     */
    @Override
    public Student queryUserInfo(Student student, Long userId)
    {
        return student;
    }
}
