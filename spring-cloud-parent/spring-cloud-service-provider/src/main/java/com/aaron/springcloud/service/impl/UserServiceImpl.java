package com.aaron.springcloud.service.impl;

import com.aaron.springcloud.api.Student;
import com.aaron.springcloud.service.UserService;
import org.springframework.stereotype.Controller;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/5/24
 */
@Controller
public class UserServiceImpl implements UserService
{
    @Override
    public Student queryUserInfo(Long userId)
    {
        Student student = new Student();
        student.setName("Li Xiaoming");
        student.setAge(23);

        return student;
    }
}
