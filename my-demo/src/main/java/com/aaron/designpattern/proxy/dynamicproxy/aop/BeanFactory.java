/**
 *
 */
package com.aaron.designpattern.proxy.dynamicproxy.aop;


import com.aaron.designpattern.proxy.dynamicproxy.Advice;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Aaron
 */
public class BeanFactory
{

    private Properties pros = new Properties();


    public BeanFactory(InputStream is)
    {
        try
        {
            pros.load(is);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public Object getBean(String name)
    {
        Class<?> clazz;
        Object bean = null;
        try
        {
            clazz = Class.forName(pros.getProperty(name));
            bean = clazz.newInstance();
            if (bean instanceof ProxyFactoryBean)
            {
                ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean)bean;
                proxyFactoryBean.setAdvice((Advice)Class.forName(pros.getProperty(name + ".advice")).newInstance());
                proxyFactoryBean.setTarget(Class.forName(pros.getProperty(name + ".target")).newInstance());
                bean = proxyFactoryBean.getProxy();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bean;
    }
}
