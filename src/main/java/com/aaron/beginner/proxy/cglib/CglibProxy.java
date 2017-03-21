package com.aaron.beginner.proxy.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-08-06
 */
public class CglibProxy implements MethodInterceptor
{
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable
    {
        System.out.println("before method " + method.getName() + " execute");

        System.out.println(methodProxy.getSuperName());

        Object object = methodProxy.invoke(o, objects);

        System.out.println("after method " + method.getName() + " execute");

        return object;
    }
}
