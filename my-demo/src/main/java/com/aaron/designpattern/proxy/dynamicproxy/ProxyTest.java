package com.aaron.designpattern.proxy.dynamicproxy; /**
 *
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Aaron
 */
public class ProxyTest
{

    /**
     * @param args
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static void main(String[] args)
            throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException
    {

        Class<?> clazzProxy = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        String name = clazzProxy.getName();

        getMethods(clazzProxy);
        getConstructors(clazzProxy);
    }


    /**
     * 获取构造方法
     *
     * @param clazz
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @SuppressWarnings ("rawtypes")
    public static void getConstructors(Class<?> clazz)
            throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException
    {
        Constructor[] constructors = clazz.getConstructors();
        StringBuilder sb;
        for (Constructor<?> constructor : constructors)
        {
            sb = new StringBuilder();
            sb.append(constructor.getName());
            sb.append("(");
            Class[] parameterTypes = constructor.getParameterTypes();
            for (Class<?> type : parameterTypes)
            {
                sb.append(type.getName());
                sb.append(",");
            }
            if (parameterTypes != null && parameterTypes.length != 0)
            {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");
            System.out.println(sb);

            System.out.println("=================create instance================");
            /**
             * 此处的构造方法带有参数，没有不带参数的构造方法，因此不能使用newInstance()
             *
             * 首先获取带参数的构造函数
             */
            Constructor<?> constructor1 = clazz.getConstructor(InvocationHandler.class);
            class MyInvocationHandler implements InvocationHandler
            {

                /* (non-Javadoc)
                 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
                 */
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                {

                    return args;
                }
            }
            InvocationHandler handler = new MyInvocationHandler();
            Collection<?> proxy1 = (Collection<?>)constructor1.newInstance(handler);

            /**
             * 此处会输出null，并不是因为对象为null，而是由于toString()返回值为null
             */
            System.out.println(proxy1);

            Collection<?> proxy2 = (Collection<?>)constructor1.newInstance(new InvocationHandler()
            {

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                {
                    return args;
                }
            });
            List<?> list = new ArrayList<Object>();
            Advice advice = new MyAdvice();
            List<String> proxy3 = (List<String>)getProxy(list, advice);
            proxy3.add("123");
            proxy3.add("13453453");
            proxy3.add("1gsfg");
            System.out.println(proxy3.size());
        }
    }


    /**
     * @return
     */
    private static Object getProxy(final Object target, final Advice advice)
    {

        Object proxyInstance = Proxy
                .newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler()
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
        return proxyInstance;
    }


    /**
     * 获取构造方法
     *
     * @param clazz
     */
    public static void getMethods(Class<?> clazz)
    {
        Method[] methods = clazz.getMethods();
        StringBuilder sb;
        for (Method method : methods)
        {
            sb = new StringBuilder();
            sb.append(method.getName());
            sb.append("(");
            Class[] parameterTypes = method.getParameterTypes();
            for (Class<?> type : parameterTypes)
            {
                sb.append(type.getName());
                sb.append(",");
            }
            if (parameterTypes != null && parameterTypes.length != 0)
            {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");
            System.out.println(sb);
        }
    }
}
