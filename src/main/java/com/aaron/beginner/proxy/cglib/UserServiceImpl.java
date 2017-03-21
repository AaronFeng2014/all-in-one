package com.aaron.beginner.proxy.cglib;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-08-06
 */
public class UserServiceImpl implements UserService
{
    public String getName(String name)
    {
        return name;
    }


    public Integer getAge(Integer age)
    {
        return age;
    }
}
