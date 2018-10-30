package com.aaron.springcloud.eureka.controller;

import com.aaron.springcloud.api.UserServiceFacade;
import com.aaron.springcloud.api.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/10/24
 */
@RestController
@RequestMapping ("students")
public class StudentController
{

    @Autowired
    private UserServiceFacade userServiceFacade;


    @GetMapping (value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@PathVariable ("id") Long id)
    {
        Student student = new Student();
        student.setName(String.valueOf(id));
        student = userServiceFacade.queryUserInfo(student, id);
        return student;
    }


    @GetMapping ("homework/{id}")
    public String getStudentHomework(@PathVariable ("id") Long id)
    {
        return "这里是学生" + id + "的家庭作业";
    }
}
