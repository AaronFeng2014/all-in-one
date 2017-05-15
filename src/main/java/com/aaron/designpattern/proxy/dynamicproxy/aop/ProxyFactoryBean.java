/**
 *
 */
package com.aaron.designpattern.proxy.dynamicproxy.aop;


import com.aaron.designpattern.proxy.dynamicproxy.Advice;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Aaron
 */
public class ProxyFactoryBean
{

    private Object target;
    private Advice advice;


    /**
     * @return the target
     */
    public Object getTarget()
    {
        return target;
    }


    /**
     * @param target the target to set
     */
    public void setTarget(Object target)
    {
        this.target = target;
    }


    /**
     * @return the advice
     */
    public Advice getAdvice()
    {
        return advice;
    }


    /**
     * @param advice the advice to set
     */
    public void setAdvice(Advice advice)
    {
        this.advice = advice;
    }


    /**
     *
     */
    public Object getProxy()
    {
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler()
        {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
            {
                advice.doBeforeMethod(method);
                Object object = method.invoke(target, args);
                advice.doAfterMethod(method);
                return object;
            }
        });
        return proxy;
    }


    public String testProxy()
    {
        System.out.println("sb");
        return null;
    }
}
