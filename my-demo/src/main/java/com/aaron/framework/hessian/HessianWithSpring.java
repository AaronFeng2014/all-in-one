package com.aaron.framework.hessian;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-05-25
 */
public class HessianWithSpring implements HessianInterface
{
    public String sayHello(String name)
    {
        return "Hello," + name;
    }
}
