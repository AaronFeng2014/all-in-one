package com.aaron.springcloud.eureka.controller;

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

    @GetMapping ("{id}")
    public String getStudent(@PathVariable ("id") Long id)
    {
        return "这里是学生" + id;
    }


    @GetMapping ("homework/{id}")
    public String getStudentHomework(@PathVariable ("id") Long id)
    {
        return "这里是学生" + id + "的家庭作业";
    }
}
