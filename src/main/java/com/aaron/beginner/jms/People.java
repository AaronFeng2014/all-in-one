package com.aaron.beginner.jms;

import java.io.Serializable;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-12-02
 */
public class People implements Serializable
{
    private String name;

    private int age;


    public People(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
}
