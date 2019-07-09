package com.aaron.beginner.proxy.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-08-06
 */
public class TestCglibProxy
{
    public static void main(String[] args)
    {
        CglibProxy cglibProxy = new CglibProxy();

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(UserServiceImpl.class);

        /**
         * NoOp.INSTANCE表示使用被代理类的实现类
         * 因为使用代理类本身，会导致递归代理，栈溢出
         */
        enhancer.setCallbacks(new Callback[] {cglibProxy, NoOp.INSTANCE});

        enhancer.setCallbackFilter(new MethodFilter());

        UserService userService = (UserService)enhancer.create();

        Integer age = userService.getAge(18);
        String name = userService.getName("Aaron");

        System.out.println(name + " is " + age);

    }


    private static class MethodFilter implements CallbackFilter
    {

        /**
         * 返回的是使用过滤器的索引
         */
        @Override
        public int accept(Method method)
        {
            if ("getAge".equals(method.getName()))
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
    }
}
