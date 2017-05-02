/**
 *
 */
package com.aaron.designpattern.proxy.dynamicproxy.aop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Aaron
 */
public class TestAop
{

    /**
     * @param args
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        InputStream is = new FileInputStream(
                "C:/Users/Aaron/Workspaces/MyEclipse 2015/proxy/src/com/westar/dynamicproxy/aop/bean.properties");
        BeanFactory factory = new BeanFactory(is);
        Object bean = factory.getBean("list");
        System.out.println(bean.getClass().getName());

        System.out.println(getSum(new int[] {1, 2, 3, 4}));
    }


    public static int getSum(int... args)
    {
        int sum = 0;
        for (int i = 0; i < args.length; i++)
        {
            sum += args[i];
        }
        return sum;
    }

}
