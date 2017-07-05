/**
 *
 */
package com.aaron.designpattern.proxy.dynamicproxy;

import java.lang.reflect.Method;

/**
 * @author Aaron
 */
public class MyAdvice implements Advice
{

    @Override
    public void doBeforeMethod(Method method)
    {
        System.out.println("doBeforeMethod" + "    " + method.getName());

    }


    @Override
    public void doAfterMethod(Method method)
    {
        System.out.println("doAfterMethod" + "    " + method.getName());

    }

}
