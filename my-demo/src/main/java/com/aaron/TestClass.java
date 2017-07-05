package com.aaron;

import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-03-30
 */
public class TestClass
{
    private static Exchanger<List<String>> exchanger = new Exchanger<>();


    public static void main(String[] args)
    {
        new Thread(getRunnable(0), "线程1").start();

        new Thread(getRunnableEmptyData(0), "线程2").start();

        new Thread(getRunnableEmptyData(0), "线程3").start();
    }


    private static Runnable getRunnable(long sleepTime)
    {
        return () ->
        {
            try
            {
                Thread.sleep(sleepTime);

                String name = Thread.currentThread().getName();

                System.out.println(name + "等待其他线程交换数据");

                List exchange = exchanger.exchange(ImmutableList.of(name));

                System.out.println(name + "收到来自" + exchange.get(0) + "的数据");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        };
    }


    private static Runnable getRunnableEmptyData(long sleepTime)
    {
        return () ->
        {
            try
            {
                Thread.sleep(sleepTime);

                String name = Thread.currentThread().getName();

                System.out.println(name + "等待其他线程交换数据");

                List exchange = exchanger.exchange(Collections.emptyList());

                System.out.println(name + "收到来自" + exchange.get(0) + "的数据");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        };
    }
}
